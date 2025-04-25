package com.gem.project.note.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.mail.MailSendException;


import com.gem.project.note.dto.ErrorResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ControllerAdvice(basePackages = "com.gem.project.note")
public class GlobalExceptionHandler {

    // java.util.logging.Logger 사용
    private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class.getName());

    // @Valid Error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorResponseDto.FieldError> errors = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> {
            log.severe("Invalid field: " + fieldError.getField() + ", message: " + fieldError.getDefaultMessage());
            ErrorResponseDto.FieldError error = new ErrorResponseDto.FieldError(fieldError.getField(), fieldError.getDefaultMessage());
            errors.add(error);
        });

        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.INVALID_INPUT_VALUE, errors);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // JPA 제약 조건 위반
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponseDto> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.severe("Data integrity violation: " + e.getMessage());

        // 예외 메시지를 통해 어떤 제약 조건이 위반되었는지 판단
        String errorMessage = "데이터가 이미 존재합니다.";
        ErrorResponseDto response;
        if (e.getMessage().contains("email")) {
            errorMessage = "이미 사용 중인 이메일입니다.";
            response = new ErrorResponseDto(ErrorCode.DUPLICATE_RESOURCE, errorMessage);
        } else if (e.getMessage().contains("nickname")) {
            errorMessage = "이미 사용 중인 닉네임입니다.";
            response = new ErrorResponseDto(ErrorCode.DUPLICATE_RESOURCE, errorMessage);
        } else {
            response = new ErrorResponseDto(ErrorCode.DUPLICATE_RESOURCE);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 인증 실패 (401)
    @ExceptionHandler(SecurityException.class) // 또는 AuthenticationException
    protected ResponseEntity<ErrorResponseDto> handleUnauthorizedException(SecurityException e) {
        log.warning("Unauthorized: " + e.getMessage());
        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.UNAUTHORIZED, "인증이 필요합니다.");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 권한 부족 (403)
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponseDto> handleAccessDeniedException(AccessDeniedException e) {
        log.warning("Forbidden: " + e.getMessage());
        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.FORBIDDEN, "접근 권한이 없습니다.");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 허용되지 않은 HTTP 메서드 (405)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponseDto> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        log.warning("Method Not Allowed: " + e.getMessage());
        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다.");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 데이터 조회 실패
    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<ErrorResponseDto> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        log.severe("Resource not found: " + e.getMessage());
        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.RESOURCE_NOT_FOUND);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    // 잘못된 요청
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
        log.severe("IllegalArgumentException: " + e.getMessage());
        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 메일 전송 실패
    @ExceptionHandler(MailSendException.class)
    protected ResponseEntity<ErrorResponseDto> handleMailSendException(MailSendException e) {
        log.severe("MailSendException: " + e.getMessage());
        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.MAIL_SEND_FAIL);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 일반적인 예외 처리
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        log.severe("Exception: " + e.getMessage());
        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.SERVER_ERROR);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
