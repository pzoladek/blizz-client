package com.pzold.blizzclient.component;

import com.pzold.blizzclient.dto.AuthenticationPayload;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class ChatStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public Type getPayloadType(StompHeaders headers) {
        System.out.println("getPayloaadTypeTEST");
        return super.getPayloadType(headers);
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        super.handleFrame(headers, payload);
        System.out.println("handleFrameTEST");
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println(session.toString());
        System.out.println("HEADERS:::::::;\n\n" + connectedHeaders.toString());

        session.subscribe("/", this);
        session.send("/", new AuthenticationPayload("key"));

    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {

        System.out.println("handleExceptionTEST");
        super.handleException(session, command, headers, payload, exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        System.out.println("handleTransproterrorTEST");
        exception.printStackTrace();
        super.handleTransportError(session, exception);
    }
}
