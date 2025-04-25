package com.gem.project.note.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 200 성공
    SUCCESS(200, "SUCCESS", "요청에 성공하였습니다."),
    CREATED(201, "CREATED", "리소스가 성공적으로 생성되었습니다."),
    NO_CONTENT(204, "NO_CONTENT", "성공했지만 반환할 내용이 없습니다."),

    // 400 클라이언트 오류
    INVALID_INPUT_VALUE(400, "BAD_REQUEST", "입력값이 올바르지 않습니다."),
    BAD_REQUEST(400, "BAD_REQUEST", "잘못된 요청입니다."),
    UNAUTHORIZED(401, "UNAUTHORIZED", "인증이 필요합니다."),
    INVALID_TOKEN(401, "UNAUTHORIZED", "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(401, "UNAUTHORIZED", "토큰이 만료되었습니다."),
    UNSUPPORTED_TOKEN(401, "UNAUTHORIZED", "지원하지 않는 토큰입니다."),
    FORBIDDEN(403, "FORBIDDEN", "접근 권한이 없습니다."),
    RESOURCE_NOT_FOUND(404, "NOT_FOUND", "해당 정보를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED", "허용되지 않은 HTTP 메서드입니다."),
    DUPLICATE_RESOURCE(409, "CONFLICT", "데이터가 이미 존재합니다."),

    // 500 서버 오류
    SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "예기치 못한 오류가 발생하였습니다."),
    MAIL_SEND_FAIL(500, "MAIL_SEND_FAIL", "이메일 전송에 실패하였습니다.");

    private final int status;
    private final String code;
    private final String message;
}
