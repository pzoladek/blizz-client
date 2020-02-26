package com.pzold.blizzclient.service;

import com.pzold.blizzclient.component.ChatStompSessionHandler;
import com.pzold.blizzclient.dto.AuthenticationPayload;
import com.pzold.blizzclient.dto.BotMessage;
import com.pzold.blizzclient.dto.Command;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Service
public class BlizzardClientService {

    private final String blizzardApiUrl;
    private final String blizzardApiKey;
    private final WebSocketStompClient webSocketClient;

    public BlizzardClientService(@Value("${blizzard.api.url}") final String blizzardApiUrl,
                                 @Value("${blizzard.api.key}") final String blizzardApiKey,
                                 final WebSocketStompClient webSocketClient) {
        this.blizzardApiUrl = blizzardApiUrl;
        this.blizzardApiKey = blizzardApiKey;
        this.webSocketClient = webSocketClient;
    }

    @PostConstruct
    public void initializeWebsocketConnection() throws Exception {
        System.out.println(blizzardApiUrl);
        final StompSessionHandler stompSessionHandler = new ChatStompSessionHandler();

        final var session = webSocketClient.connect(blizzardApiUrl, stompSessionHandler).get();

        session.subscribe("", stompSessionHandler);
        var result = session.send("", BotMessage.builder()
                .command(Command.AUTHENTICATION)
                .payload(new AuthenticationPayload(blizzardApiKey)).requestId(1));

        new Scanner(System.in).nextLine();

    }


}
