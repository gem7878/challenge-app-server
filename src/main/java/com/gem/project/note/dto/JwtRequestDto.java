package com.gem.project.note.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestDto {
    @NotEmpty(message = "refreshToken을 입력해주세요.")
    private String refreshToken;
}
