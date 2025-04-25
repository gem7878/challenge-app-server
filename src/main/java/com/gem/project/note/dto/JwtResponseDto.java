package com.gem.project.note.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "JWT 응답 DTO")
public class JwtResponseDto {
    @Schema(description = "액세스토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzd...")
    private String accessToken;
    @Schema(description = "리프레시토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzd...")
    private String refreshToken;
    @Schema(description = "액세스토큰 만료시간", example = "3600")
    private Long expiresIn;
}
