package com.gem.project.note.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "사용할 수 있는 이메일 응답 DTO")
public class MemberEmailResponseDto {
    
    @Schema(description = "현재 이메일")
    private String email;

    @Schema(description = "이메일 중복 여부, false이면 사용할 수 있음", example = "true")
    private boolean existsEmail;
}
