package com.gem.project.note.dto;

import com.gem.project.note.entity.Member;
import com.gem.project.note.entity.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "회원 정보 응답 DTO")
public class MemberResponseDto {

    @Schema(description = "회원 이메일")
    private String email;

    @Schema(description = "회원 닉네임")
    private String nickname;

    @Schema(description = "회원 권한")
    private Role role;
    
    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .role(member.getRole())
                .build();
    }
}
