package com.gem.project.note.dto;

import com.gem.project.note.entity.Member;
import com.gem.project.note.entity.Role;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCreateRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식으로 작성해주세요.")
    private String email;
    
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 3, max = 15, message = "닉네임은 3 ~ 15자 입니다.")
    private String nickname;

    private Role role;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .role(Objects.nonNull(role) && Role.ROLE_ADMIN.equals(role) ? Role.ROLE_ADMIN : Role.ROLE_USER)
                .build();     
    }
}
