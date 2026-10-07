package org.example.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;



public class BrokerImpl {
    private final static Logger logger = LoggerFactory.getLogger(BrokerImpl.class);
    private int port = 8080;
    private Server server;

    public BrokerImpl(int port){
        this.port = port;

        this.server = ServerBuilder.forPort(this.port)
                .addService(new ServerServiceImpl())
                .build();
    }

    public BrokerImpl(){
        this.server = ServerBuilder.forPort(this.port)
                .addService(new ServerServiceImpl())
                .build();
    }

    public void start_con(){
        try {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.error("[***] shutting down gRPC server since JVM is shutting down");
                server.shutdown();
                logger.error("[***] server shutdown");
            }));

            this.server.start();
            logger.info("[INFO | BrokerImpl] Server starts at port {}", this.port);

            this.server.awaitTermination();

        } catch (IOException | InterruptedException e) {
            logger.error("[ERROR | BrokerImpl] -> {}", e.getMessage());

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
