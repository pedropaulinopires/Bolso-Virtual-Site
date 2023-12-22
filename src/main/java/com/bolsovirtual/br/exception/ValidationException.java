package com.bolsovirtual.br.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class ValidationException extends ExceptionDetails{

    private List<String> fields;
    private List<String> fieldsMessage;
}