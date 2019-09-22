package com.pzold.blizzclient.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BotMessage {

    @JsonProperty("command")
    private final Command command;
    @JsonProperty("request_id")
    private final Integer requestId;
    @JsonProperty("payload")
    private final Payload payload;
    @JsonProperty("status")
    private final Status status;

    @JsonCreator
    public BotMessage(@JsonProperty("command") final Command command,
                      @JsonProperty("request_id") final Integer requestId,
                      @JsonProperty("payload") final Payload payload,
                      @JsonProperty("status") final Status status) {
        this.command = command;
        this.requestId = requestId;
        this.payload = payload;
        this.status = status;
    }
}
