package com.bolsovirtual.br.handler;

import com.bolsovirtual.br.exception.ExceptionDetails;
import com.bolsovirtual.br.exception.ValidationException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerExceptionRequest extends ResponseEntityExceptionHandler {

    private static final LocalDateTime TIME = LocalDateTime.now();

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> handlerBadRequestException(BadRequestException exception) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timeStamp(TIME)
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad request exception, check documentation")
                .message(exception.getMessage())
                .messageDeveloper(exception.getClass().getName()).build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> fields = fieldErrors.stream().map(FieldError::getField).distinct().toList();
        List<String>  fieldsMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(
                ValidationException.builder()
                        .timeStamp(TIME)
                        .status(exception.getStatusCode().value())
                        .error("filed(s) not valid, check out!")
                        .message(exception.getMessage())
                        .messageDeveloper(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessages)
                        .build()
                , exception.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers
            , HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timeStamp(TIME)
                .status(status.value())
                .error(ex.getCause().toString())
                .message(ex.getMessage())
                .messageDeveloper(ex.getClass().getName())
                .build(), status);
    }
}
