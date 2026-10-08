package org.example.service;


import org.example.service.models.enums.VulnerabilityEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ServerServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(ServerServiceImplTest.class);
    private ServerServiceImpl service;


    @BeforeEach
    void setUp() {
        service = new ServerServiceImpl();
    }

    // Constructor
    // 0. constructor initializes subscribers map for all VulnerabilityEnum values except UNKNOWN
    @Test
    public void checkMapIntegrity(){
        var map = service.getMap();

        for(VulnerabilityEnum type : VulnerabilityEnum.values()){

        }
    }

    // publishIncident
    // 1. publishIncident — success, with active subscriber receiving the event
    // 2. publishIncident — success, with NO subscribers for topic (no exception, just logs)
    // 3. publishIncident — one subscriber throws on onNext, gets removed from the set
    // 4. publishIncident — multiple subscribers for same topic all receive the message

    // subscribeToIncidents
    // 5. subscribeToIncidents — success, observer added to correct topic set
    // 6. subscribeToIncidents — unknown topic, onError called with INVALID_ARGUMENT, onNext/onCompleted never called
    // 7. subscribeToIncidents — cancellation triggers removal from subscribers set
}