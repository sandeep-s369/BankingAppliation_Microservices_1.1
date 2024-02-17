package com.nagarro.banking.customers.exception;

//
//import java.util.Date;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.ErrorResponse;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.NoHandlerFoundException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<ErrorResponse> handleNotFoundException(NoHandlerFoundException ex) {
//        ErrorResponse error = new ErrorResponseException(HttpStatus.NOT_FOUND.value(), "Page not found", new Date());
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//}
