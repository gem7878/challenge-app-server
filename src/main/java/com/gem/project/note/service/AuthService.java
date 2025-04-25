package com.gem.project.note.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gem.project.note.dto.JwtResponseDto;
import com.gem.project.note.dto.LoginRequestDto;
import com.gem.project.note.dto.MemberCreateRequest;
import com.gem.project.note.dto.MemberEmailResponseDto;
import com.gem.project.note.dto.MemberNicknameResponseDto;
import com.gem.project.note.dto.MemberResponseDto;
import com.gem.project.note.entity.Member;
import com.gem.project.note.entity.RefreshToken;
import com.gem.project.note.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final RefreshTokenService refreshTokenService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    

    // 회원가입
    public MemberResponseDto signup(MemberCreateRequest requestDto) {
        Member newMember = requestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(newMember));
    }

    // 이메일 중복 확인
    public MemberEmailResponseDto confirmDuplicateEmail(String email) {
        boolean existsEmail = memberRepository.existsByEmail(email);
        if (!existsEmail) {
            throw new DataIntegrityViolationException("이미 사용 중인 이메일입니다.");
        } 
        return new MemberEmailResponseDto(email, existsEmail);
    }

    // 닉네임 중복확인
    public MemberNicknameResponseDto confirmDuplicateNickname(String nickname) {       
        boolean existsNickname = memberRepository.existsByNickname(nickname);
        if (!existsNickname) {
            throw new DataIntegrityViolationException("이미 사용 중인 닉네임입니다.");
        }
        return new MemberNicknameResponseDto(nickname, existsNickname);
    }

    // 로그인
    public JwtResponseDto login(LoginRequestDto requestDto) {
        Optional<Member> member = memberRepository.findByEmail(requestDto.getEmail());

        if (member.isEmpty()) {
            throw new EmptyResultDataAccessException("이메일 또는 비밀번호가 잘못 되었습니다.", 1);
        }

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(member.get());

        return refreshTokenService.createAccessToken(refreshToken.getToken(), requestDto.getEmail(), requestDto.getPassword());
    }
}