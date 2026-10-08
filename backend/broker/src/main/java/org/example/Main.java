package org.example;


import org.example.service.BrokerImpl;


public class Main {
    public static void main(String[] args) {
        System.out.println("========= SOC-Broker =========");
        System.out.println("Version: 0.0.1 - ColdAsf");

        BrokerImpl broker = new BrokerImpl();
        broker.start_con();
    }
}