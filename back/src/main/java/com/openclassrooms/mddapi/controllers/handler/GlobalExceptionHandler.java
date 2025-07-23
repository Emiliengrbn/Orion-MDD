package com.openclassrooms.mddapi.controllers.handler;

import com.openclassrooms.mddapi.DTO.MessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponseDTO> handleForbidden(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponseDTO(illegalArgumentException.getMessage()));
    }
}
