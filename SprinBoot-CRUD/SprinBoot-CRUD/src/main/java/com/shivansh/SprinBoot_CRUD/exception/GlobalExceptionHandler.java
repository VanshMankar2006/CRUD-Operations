package com.shivansh.SprinBoot_CRUD.exception;

import com.shivansh.SprinBoot_CRUD.dto.ExceptionResponseDto;
import com.shivansh.SprinBoot_CRUD.dto.ValidationExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDto>ValidationException(
            MethodArgumentNotValidException e){

        Map<String,String> fieldError = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(errors->
                        fieldError.put(errors.getField(), errors.getDefaultMessage()));

        ValidationExceptionResponseDto dto = new ValidationExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation failed ",
                fieldError
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(dto);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ExceptionResponseDto>DuplicateRecordEx(DuplicateRecordException e){
        ExceptionResponseDto dto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                e.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(dto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto>ResourceNotFoundEx(ResourceNotFoundException e){
        ExceptionResponseDto dto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(dto);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto>runtimeExceptionHandler(RuntimeException e){
        ExceptionResponseDto dto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(dto);

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto>genericExceptionHandler(Exception e){
        ExceptionResponseDto dto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(dto);
    }
}
