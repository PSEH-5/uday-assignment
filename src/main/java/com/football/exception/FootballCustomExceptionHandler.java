package com.football.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.football.pojos.ErrorBody;

@ControllerAdvice
public class FootballCustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(FootballException ex, WebRequest request) {
       
        ErrorBody error=new ErrorBody();
        error.setErrorcode(HttpStatus.NOT_FOUND.toString());
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
