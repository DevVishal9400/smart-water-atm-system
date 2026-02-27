package com.vt.water.atm.exception;

import com.vt.water.atm.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Map>> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {

        log.warn("User Already Exists With Mobile :{}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(false, exception.getMessage(), null, LocalDateTime.now()));


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map>> handleMethodArgsNotValidException(MethodArgumentNotValidException exception) {
        log.warn("Validation failed for request: {}", exception.getMessage());
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        Map<String, String> errormap = new HashMap<>();
        for (ObjectError e : allErrors) {
            String errorField = ((FieldError) e).getField();
            String errorMsg = e.getDefaultMessage();
            errormap.put(errorField, errorMsg);
            log.debug("Validation error - Field: {}, Message: {}", errorField, errorMsg);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<Map>(false, "Invalid Arguments", errormap, LocalDateTime.now()));


    }
}
