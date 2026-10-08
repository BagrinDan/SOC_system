package org.example.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
    Central class that starts server on default port 8080.
    It has 2 constructors with default port or specific (if u want).

    P.S: We're not extends abstract Server because we don't need all those methods,
    but yes, it would be better to do so for a real app.
 */

public class BrokerImpl {
    private final static Logger logger = LoggerFactory.getLogger(BrokerImpl.class);
    private int port = 8080;
    private final Server server;

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

    /**
        Simple main method that starts server.
        From important: It has hook (Runtime) that will shoutdown server properly,
        and 'Thread.currentThread().interrupt' to not swallow 'true' flag accidentally
     */
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
