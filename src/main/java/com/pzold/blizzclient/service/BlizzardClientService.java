package com.pzold.blizzclient.service;

import com.pzold.blizzclient.dto.AuthenticationPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;

@Service
public class BlizzardClientService {

    private final String blizzardApiUrl;
    private final WebSocketStompClient webSocketClient;

    public BlizzardClientService(@Value("${blizzard.api.url}") final String blizzardApiUrl,
                                 final WebSocketStompClient webSocketClient) {
        this.blizzardApiUrl = blizzardApiUrl;
        this.webSocketClient = webSocketClient;
    }

    @PostConstruct
    public void initializeWebsocketConnection() {
        System.out.println(blizzardApiUrl);
        webSocketClient.connect(blizzardApiUrl, new ChatStompSessionHandler());
        //TODO: after all beans has settled (delegate connecting to websocket)
    }


    private class ChatStompSessionHandler extends StompSessionHandlerAdapter {

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return super.getPayloadType(headers);
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            super.handleFrame(headers, payload);
        }

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            System.out.println(session.toString());
            System.out.println("HEADERS:::::::;\n\n" + connectedHeaders.toString());

            session.subscribe("wss://connect-bot.classic.blizzard.com/v1/rpc/chat", this);
            session.send("wss://connect-bot.classic.blizzard.com/v1/rpc/chat", new AuthenticationPayload("key"));

        }

        @Override
        public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
            super.handleException(session, command, headers, payload, exception);
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            super.handleTransportError(session, exception);
        }
    }
}
