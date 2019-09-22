package com.pzold.blizzclient.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationPayload extends Payload {

    @JsonProperty("api_key")
    private final String apiKey;

    @JsonCreator
    public AuthenticationPayload(@JsonProperty("api_key") final String apiKey) {
        this.apiKey = apiKey;
    }
}
