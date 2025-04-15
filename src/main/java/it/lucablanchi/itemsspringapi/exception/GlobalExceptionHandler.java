package it.lucablanchi.itemsspringapi.exception;

import it.lucablanchi.itemsspringapi.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundApiException.class)
    public ResponseEntity<?> handleNotFound(NotFoundApiException ex) {
        int statusCode = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(statusCode).body(
                ApiResponse.error(statusCode, ex.getMessage(), null)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(
                ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        return ResponseEntity.status(statusCode).body(
                ApiResponse.error(statusCode, ex.getMessage(), null)
        );
    }
}
