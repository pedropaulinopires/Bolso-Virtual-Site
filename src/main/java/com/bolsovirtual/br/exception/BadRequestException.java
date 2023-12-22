package com.bolsovirtual.br.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}