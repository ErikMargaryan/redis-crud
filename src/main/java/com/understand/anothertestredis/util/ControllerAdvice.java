package com.understand.anothertestredis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Date;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

//    @ExceptionHandler({DuplicateKeyException.class})
//    private ResponseEntity<Object> handlerIllegalArgument(DuplicateKeyException ex, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(
//                new Date(),
//                ex.getClass().getSimpleName(),
//                ex.getMessage(),
//                request.getDescription(false)
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
//    }

//    @ExceptionHandler({ValidationException.class})
//    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
//        List<String> details = new ArrayList<>();
//        details.add(ex.getLocalizedMessage());
//        ErrorDetails error = new ErrorDetails(
//                new Date(),
//                ex.getClass().getSimpleName(),
//                ex.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler({ConstraintViolationException.class})
//    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
//        logger.info(ex.getClass().getName());
//        List<String> details = new ArrayList<>();
//        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//            details.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
//        }
//        ErrorDetails error = new ErrorDetails(
//                new Date(),
//                ex.getClass().getSimpleName(),
//                ex.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(CustomEx.class)
//    public ResponseEntity<Object> processUnmergeException(final CustomEx ex) {
//
//        ErrorDetails error = new ErrorDetails(
//                new Date(),
//                ex.getClass().getSimpleName(),
//                ex.getMessage(),
//                null);
//        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> processUnmergeException1(MethodArgumentNotValidException ex) {

        ErrorDetails error = new ErrorDetails(
                new Date(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
