package main

import (
	"context"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"time"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"

	pb "lab2/proto"
)

const (
	brokerAddr = "localhost:8081"
	httpPort   = ":8092"
)

type IncidentHTTPPayload struct {
	SourceIP       string `json:"source_ip"`
	Payload        string `json:"payload"`
	TargetEndpoint string `json:"target_endpoint"`
}

type PubServer struct {
	brokerClient pb.IncidentBrokerClient
}

func main() {
	conn, err := grpc.NewClient(brokerAddr, grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		log.Fatalf("[Pub2-XSS] Ошибка подключения к Broker: %v", err)
	}
	defer conn.Close()

	pub := &PubServer{
		brokerClient: pb.NewIncidentBrokerClient(conn),
	}

	http.HandleFunc("/api/xss", pub.handleXSSIncident)

	log.Printf("[Pub2-XSS] Сервер запущен на порту %s...", httpPort)
	if err := http.ListenAndServe(httpPort, enableCORS(http.DefaultServeMux)); err != nil {
		log.Fatalf("[Pub2-XSS] Ошибка сервера: %v", err)
	}
}

func (p *PubServer) handleXSSIncident(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Метод не поддерживается", http.StatusMethodNotAllowed)
		return
	}

	var req IncidentHTTPPayload
	if err := json.NewDecoder(r.Body).Decode(&req); err != nil {
		http.Error(w, "Некорректный JSON", http.StatusBadRequest)
		return
	}

	// Формируем IncidentRequest согласно твоему .proto
	msg := &pb.IncidentRequest{
		Type:           pb.VulnerabilityType_XSS, // Жёстко задаем тип XSS
		SourceIp:       req.SourceIP,
		Payload:        req.Payload,
		TargetEndpoint: req.TargetEndpoint,
		MessageId:      fmt.Sprintf("xss-%d", time.Now().UnixNano()),
		Timestamp:      time.Now().Unix(),
	}

	// Вызываем gRPC-метод PublishIncident у брокера
	resp, err := p.brokerClient.PublishIncident(context.Background(), msg)
	if err != nil {
		log.Printf("[Pub2-XSS] Ошибка отправки в Broker: %v", err)
		http.Error(w, "Ошибка отправки в брокер", http.StatusInternalServerError)
		return
	}

	log.Printf("[Pub2-XSS] Опубликован XSS инцидент ID=%s | Status=%s", resp.GetMessageId(), resp.GetStatus().String())

	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(map[string]interface{}{
		"success":    resp.GetSuccess(),
		"message_id": resp.GetMessageId(),
		"status":     resp.GetStatus().String(),
	})
}

func enableCORS(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.Header().Set("Access-Control-Allow-Origin", "*")
		w.Header().Set("Access-Control-Allow-Methods", "POST, OPTIONS")
		w.Header().Set("Access-Control-Allow-Headers", "Content-Type")

		if r.Method == http.MethodOptions {
			w.WriteHeader(http.StatusOK)
			return
		}
		next.ServeHTTP(w, r)
	})
}