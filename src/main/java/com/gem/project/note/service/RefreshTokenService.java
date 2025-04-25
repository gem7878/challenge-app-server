package com.gem.project.note.service;


import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.gem.project.note.dto.JwtRequestDto;
import com.gem.project.note.dto.JwtResponseDto;
import com.gem.project.note.dto.TokenDto;
import com.gem.project.note.entity.*;
import com.gem.project.note.repository.MemberRepository;
import com.gem.project.note.repository.RefreshTokenRepository;
import com.gem.project.note.jwt.TokenProvider;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {
    
    private final AuthenticationManagerBuilder managerBuilder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public RefreshToken createRefreshToken(Member member) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .member(member)
                .expiryDate(LocalDateTime.now().plusDays(7))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    // 새로운 Access Token 발급
    public JwtResponseDto createAccessToken(String refreshToken, String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = tokenProvider.toAuthentication(email, password);
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
        TokenDto newAccessToken = tokenProvider.createJwt(authentication,24);

        return JwtResponseDto.builder()
                .accessToken(newAccessToken.getAccessToken())
                .refreshToken(refreshToken)
                .expiresIn(newAccessToken.getTokenExpiresIn())
                .build();
    }

    // Access Token 기간 만료 시 재발급
    public JwtResponseDto verifyExpiration(JwtRequestDto jwtRequestDto) throws Exception {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(jwtRequestDto.getRefreshToken());
        Member member;

        // Refresh Token이 존재하지 않거나 만료된 경우
        if (refreshToken == null || refreshToken.isExpired()) {
            // 토큰으로 회원 조회
            member = (refreshToken != null) 
                    ? refreshToken.getMember() 
                    : findMemberByToken(jwtRequestDto.getRefreshToken());

            // 새로운 Refresh Token 생성 및 저장
            refreshToken = createRefreshToken(member);
        } else {
            // 기존 Refresh Token이 유효한 경우, 회원 정보 가져오기
            member = refreshToken.getMember();
        }

        // Access Token 재발급
        return createAccessToken(refreshToken.getToken(), member.getEmail(), member.getPassword());
    }


    private Member findMemberByToken(String token) throws Exception {
        // Refresh Token을 DB에서 찾을 수 없는 경우, 토큰의 정보를 디코딩하여 사용자 조회
        Claims claims = tokenProvider.validateAccessToken(token);
        Long memberId = Long.parseLong(claims.getSubject());

        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")); // 비정상적인 접근
    }
}
