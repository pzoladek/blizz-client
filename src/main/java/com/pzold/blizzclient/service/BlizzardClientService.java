package com.pzold.blizzclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.PostConstruct;

@Service
public class BlizzardClientService {

    private final String blizzardApiUrl;
    private final WebSocketClient webSocketClient;

    public BlizzardClientService(final WebSocketClient webSocketClient,
                                 @Value("${blizzard.api.url}") final String blizzardApiUrl) {
        this.webSocketClient = webSocketClient;
        this.blizzardApiUrl = blizzardApiUrl;
    }

    @PostConstruct
    public void initializeWebsocketConnection() {
        System.out.println(blizzardApiUrl);
    }


}
