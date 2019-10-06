package com.example.BookShoop.Spring.controllers;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    protected ResponseEntity<Object> hendlerIllegalArgumentException(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(),new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(value = {SQLException.class})
    @ResponseBody
    protected ResponseEntity<Object> hendlerSQLException(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(),new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {PSQLException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request){

        return handleExceptionInternal(ex,ex.getMessage(),new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
