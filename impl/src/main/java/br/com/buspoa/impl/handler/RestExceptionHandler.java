package br.com.buspoa.impl.handler;

import br.com.buspoa.impl.error.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Details> handlerNotFoud(NotFound notFound) {
        Details found = Details.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Not Found")
                .detail(notFound.getMessage())
                .developerMessage(notFound.getClass().getName())
                .build();
        return new ResponseEntity<>(found, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoContent.class)
    public ResponseEntity<Details> handlerNoContent(NoContent noContent) {
        Details details = Details.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NO_CONTENT.value())
                .title("No Content")
                .detail(noContent.getMessage())
                .developerMessage(noContent.getClass().getName())
                .build();
        return new ResponseEntity<>(details, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<Details> handlerUnprocessableEntity(UnprocessableEntity unprocessableEntity) {
        Details details = Details.builder()
                .timestamp(new Date().getTime())
                .status((HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .title("Unprocessable Entity")
                .detail(unprocessableEntity.getMessage())
                .developerMessage(unprocessableEntity.getClass().getName())
                .build();
        return new ResponseEntity<>(details, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Details details = Details.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Not Found")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Details details = Details.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .title("Method Not Allowed")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(details, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Details details = Details.builder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Exceptional Internal")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(details, headers, status);
    }
}
