package com.gem.project.note.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import com.gem.project.note.exception.ErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@AllArgsConstructor
@Schema(description = "에러 응답 DTO")
public class ErrorResponseDto {
    @Schema(description = "HTTP 상태 코드", example = "400")
    private int status;
    @Schema(description = "에러 코드", example = "INVALID_INPUT_VALUE")
    private String code;
    @Schema(description = "에러 메시지", example = "입력값이 올바르지 않습니다.")
    private String message;
    @Schema(description = "@Valid 검증 실패 리스트", example = "[{\"field\":\"email\",\"message\":\"이메일 형식이 올바르지 않습니다.\"}]", nullable = true)
    private List<FieldError> fieldErrors;

    public ErrorResponseDto(ErrorCode errorCode, String message) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = message;
    }

    public ErrorResponseDto(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ErrorResponseDto(ErrorCode errorCode, List<FieldError> fieldErrors) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.fieldErrors = fieldErrors;
    }
    

    @Getter
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private String message;
    }
}
