package com.deepeningInSpringBoot.infra;

import com.deepeningInSpringBoot.dto.ExeptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExeptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity duplicateEntry(DataIntegrityViolationException dataExpetion){
        ExeptionDTO exeptionDTO = new ExeptionDTO("Usuário já cadastrado", "400");
        return ResponseEntity.badRequest().body(exeptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity entityNotFound(EntityNotFoundException dataExpetion){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity geralExepiton(Exception exception){
        ExeptionDTO exeptionDTO = new ExeptionDTO(exception.getMessage(), "400");
        return ResponseEntity.internalServerError().body(exeptionDTO);
    }
}
