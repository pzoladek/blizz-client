package com.pzold.blizzclient.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Command {
    AUTHENTICATION("Botapiauth.AuthenticateRequest");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
