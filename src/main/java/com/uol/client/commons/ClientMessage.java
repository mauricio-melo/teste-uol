package com.uol.client.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientMessage {

    BAD_CREDENTIALS("Bad credentials"),

    RESOURCE_ALREADY_EXISTS("Resource already exists"),

    SERVER_ERROR("An unexpected error has occurred. Please try again"),

    RESOURCE_NOT_FOUND("Requested resource could not be found");

    private final String message;
}
