package com.gem.project.note.dto;

import java.time.LocalDateTime;

import com.gem.project.note.exception.ErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder

@Schema(description = "API 응답 DTO")
public class ApiResponseDto<T> {

    @Schema(description = "응답 시간")
    private LocalDateTime timestamp;

    @Schema(description = "성공 여부", example = "true")
    private boolean success;

    @Schema(description = "HTTP 상태 코드", example = "200")
    private int status;

    @Schema(description = "에러 코드", example = "SUCCESS")
    private String code;

    @Schema(description = "메세지", example = "요청에 성공하였습니다.")
    private String message;

    @Schema(description = "response data")
    private T data;

    public static <T> ApiResponseDto<T> success(ErrorCode errorCode, String message, T data) {
        return ApiResponseDto.<T>builder()
                .timestamp(LocalDateTime.now())
                .success(true)
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(message)
                .data(data)
                .build();
    }
}
