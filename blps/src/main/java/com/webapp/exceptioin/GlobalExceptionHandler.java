package com.webapp.exceptioin;

import com.webapp.dto.MessageErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<MessageErrorDto> catchResourceNotFoundException(Exception e) {
        return new ResponseEntity<>(new MessageErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({ResourceAlreadyExistsException.class})
    public ResponseEntity<MessageErrorDto> catchResourceAlreadyExistsException(Exception e) {
        return new ResponseEntity<>(new MessageErrorDto(HttpStatus.CONFLICT.value(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ResourceNotAllowedException.class})
    public ResponseEntity<MessageErrorDto> catchResourceNotAllowedException(Exception e) {
        return new ResponseEntity<>(new MessageErrorDto(HttpStatus.FORBIDDEN.value(), e.getMessage()), HttpStatus.FORBIDDEN);
    }
}
