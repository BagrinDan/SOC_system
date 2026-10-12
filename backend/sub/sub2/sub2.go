package main

import (
	"context"
	"database/sql"
	"fmt"
	"io"
	"log"
	"time"

	_ "github.com/lib/pq"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"

	pb "lab2/proto"
)

const (
	brokerAddr = "localhost:8080" 
	dbHost     = "localhost"
	dbPort     = 5432
	dbUser     = "soc_user"
	dbPassword = "soc_password"
	dbName     = "incidents"
)

func main() {
	connStr := fmt.Sprintf("host=%s port=%d user=%s password=%s dbname=%s sslmode=disable",
		dbHost, dbPort, dbUser, dbPassword, dbName)

	db, err := sql.Open("postgres", connStr)
	if err != nil {
		log.Fatalf("[Sub2-XSS] Ошибка конфигурации БД: %v", err)
	}
	defer db.Close()

	if err := db.Ping(); err != nil {
		log.Fatalf("[Sub2-XSS] Нет подключения к Postgres: %v", err)
	}
	log.Println("[Sub2-XSS] Успешно подключено к PostgreSQL")

	conn, err := grpc.NewClient(brokerAddr, grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		log.Fatalf("[Sub2-XSS] Не удалось подключиться к брокеру: %v", err)
	}
	defer conn.Close()

	client := pb.NewIncidentBrokerClient(conn)

	log.Println("[Sub2-XSS] Подписка на стрим инцидентов XSS...")
	stream, err := client.SubscribeToIncidents(context.Background(), &pb.SubscribeRequest{
		Type: pb.VulnerabilityType_XSS,
	})
	if err != nil {
		log.Fatalf("[Sub2-XSS] Ошибка вызова SubscribeToIncidents: %v", err)
	}

	for {
		msg, err := stream.Recv()
		if err == io.EOF {
			log.Println("[Sub2-XSS] Брокер закрыл стрим")
			break
		}
		if err != nil {
			log.Printf("[Sub2-XSS] Ошибка чтения из стрима: %v. Повтор через 2 сек...", err)
			time.Sleep(2 * time.Second)
			continue
		}

		log.Printf("[Sub2-XSS] Получен XSS инцидент: ID=%s | Target=%s", msg.GetMessageId(), msg.GetTargetEndpoint())

		// 5. Запись в Postgres
		if err := saveXSSToDB(db, msg); err != nil {
			log.Printf("[Sub2-XSS] Ошибка сохранения в БД: %v", err)
		} else {
			log.Printf("[Sub2-XSS] Успешно сохранен инцидент %s в таблицу xss_incidents", msg.GetMessageId())
		}
	}
}

func saveXSSToDB(db *sql.DB, msg *pb.IncidentResponse) error {
	query := `
		INSERT INTO xss_incidents (message_id, vulnerability_type, source_ip, target_endpoint, payload)
		VALUES ($1, $2, $3, $4, $5)
		ON CONFLICT (message_id) DO NOTHING;
	`
	_, err := db.Exec(query,
		msg.GetMessageId(),
		msg.GetType().String(),
		msg.GetSourceIp(),
		msg.GetTargetEndpoint(),
		msg.GetPayload(),
	)
	return err
}