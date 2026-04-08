package com.sanrio.micromarket.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, String> errores = new HashMap<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(fe -> errores.put(fe.getField(), fe.getDefaultMessage()));
    Map<String, Object> body = new HashMap<>();
    body.put("mensaje", "Errores de validación");
    body.put("errores", errores);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, String>> handleIntegrity(DataIntegrityViolationException ex) {
    Map<String, String> body = new HashMap<>();
    body.put(
        "mensaje",
        "Violación de integridad de datos (posible duplicado de código de barras o NIT)");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }
}
