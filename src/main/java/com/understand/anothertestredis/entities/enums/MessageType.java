package com.understand.anothertestredis.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MessageType implements Serializable {


    CHAT("CHAT"),
    JOIN("JOIN"),
    LEAVE("LEAVE");

    private final String name;

    MessageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonCreator
    public static MessageType fromValue(@JsonProperty("name") String name) {
        try {
            return valueOf(name);
        } catch (Exception ex) {
            return null;
        }
    }

}
