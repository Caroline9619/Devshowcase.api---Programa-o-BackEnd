package br.com.csm.devshowcase.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
public ResponseEntity<Map<String, Object>> tratarMetodoNaoPermitido(
        org.springframework.web.HttpRequestMethodNotSupportedException ex) {

    Map<String, Object> erro = new HashMap<>();

    erro.put("timestamp", LocalDateTime.now());
    erro.put("status", 405);
    erro.put("erro", "Método não permitido");
    erro.put("mensagem", ex.getMessage());

    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
}
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> tratarNaoEncontrado(
            ResourceNotFoundException ex) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", 404);
        erro.put("erro", "Recurso não encontrado");
        erro.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> tratarRegraNegocio(
            BusinessException ex) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", 400);
        erro.put("erro", "Regra de negócio");
        erro.put("mensagem", ex.getMessage());

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(
            MethodArgumentNotValidException ex) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", 400);
        erro.put("erro", "Erro de validação");

        Map<String, String> campos = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(e -> campos.put(e.getField(), e.getDefaultMessage()));

        erro.put("campos", campos);

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> tratarErroGeral(
            Exception ex) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", 500);
        erro.put("erro", "Erro interno");
        erro.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);
    }
}