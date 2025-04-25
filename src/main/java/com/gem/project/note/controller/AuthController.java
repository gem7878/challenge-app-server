package com.gem.project.note.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gem.project.note.dto.ApiResponseDto;
import com.gem.project.note.dto.ErrorResponseDto;
import com.gem.project.note.dto.JwtRequestDto;
import com.gem.project.note.dto.JwtResponseDto;
import com.gem.project.note.dto.LoginRequestDto;
import com.gem.project.note.dto.MemberCreateRequest;
import com.gem.project.note.dto.MemberEmailRequestDto;
import com.gem.project.note.dto.MemberEmailResponseDto;
import com.gem.project.note.dto.MemberNicknameRequestDto;
import com.gem.project.note.dto.MemberResponseDto;
import com.gem.project.note.exception.ErrorCode;
import com.gem.project.note.service.AuthService;
import com.gem.project.note.service.RefreshTokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    
    @Operation(summary = "회원 가입")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "회원 가입 성공", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ApiResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
        @ApiResponse(responseCode = "500", description = "서버 오류",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<MemberResponseDto>> signup(@Valid @RequestBody MemberCreateRequest request) {
        MemberResponseDto member = authService.signup(request);
        return ResponseEntity.ok(ApiResponseDto.success(ErrorCode.CREATED, "회원 가입 성공", member));
    }


    @Operation(summary = "로그인")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "로그인 성공", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ApiResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "입력값 오류", 
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
        @ApiResponse(responseCode = "401", description = "인증 실패", 
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
        @ApiResponse(responseCode = "500", description = "서버 오류", 
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<JwtResponseDto>> login(@Valid @RequestBody LoginRequestDto request) {
        JwtResponseDto jwtResponse = authService.login(request);
        return ResponseEntity.ok(ApiResponseDto.success(ErrorCode.SUCCESS, "로그인 성공", jwtResponse));
    }
    

    @Operation(summary = "이메일 중복 확인", description = "exists_email : false(사용 가능함)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "exists_email : false(사용 가능함)", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ApiResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "입력값 오류", 
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
        @ApiResponse(responseCode = "409", description = "중복 오류", 
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
        @ApiResponse(responseCode = "500", description = "서버 오류", 
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/email")
    public ResponseEntity<ApiResponseDto<?>> confirmEmail(@Valid @RequestBody MemberEmailRequestDto requestDto) {
        MemberEmailResponseDto  emailResponse = authService.confirmDuplicateEmail(requestDto.getEmail());
        return ResponseEntity.ok(ApiResponseDto.success(ErrorCode.SUCCESS, "사용 가능한 이메일입니다.", emailResponse));
    }

    @Operation(summary = "닉네임 중복 확인", description = "success : true, exists_nickname : false (사용 가능함)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "success : true, exists_nickname : false (사용 가능함)", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ApiResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "입력값 오류", 
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
        @ApiResponse(responseCode = "409", description = "중복 오류", 
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
        @ApiResponse(responseCode = "500", description = "서버 오류", 
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/nickname")
    public ResponseEntity<ApiResponseDto<?>> confirmNickname(@Valid @RequestBody MemberNicknameRequestDto requestDto) {
        MemberEmailResponseDto  emailResponse = authService.confirmDuplicateEmail(requestDto.getNickname());
        return ResponseEntity.ok(ApiResponseDto.success(ErrorCode.SUCCESS, "사용 가능한 닉네임입니다.", emailResponse));
    }

    /*
    @PostMapping("/email/verification-request")
    public ResponseEntity<CommonResponseDto> verificationEmail(@Valid @RequestBody ConfirmEmailRequestDto requestDto) {
        authService.confirmDuplicateEmail(requestDto.getEmail());
        authService.sendCodeToEmail(requestDto.getEmail());
        return ResponseEntity.ok(new CommonResponseDto(true, "이메일을 전송하였습니다."));
    }

    @GetMapping("/email/verification")
    public ResponseEntity<CommonResponseDto> verificationCode(@RequestParam(name = "email") String email, 
                                                                @RequestParam(name = "code") String code) {
        return ResponseEntity.ok(new CommonResponseDto(authService.verifiedCode(email, code), "인증되었습니다."));
    }

    // 비밀번호 찾기
    @PostMapping("/password/find")
    public ResponseEntity<CommonResponseDto> findPassword(@Valid @RequestBody ConfirmEmailRequestDto requestDto) {
        authService.sendPasswordToEmail(requestDto.getEmail());
        return ResponseEntity.ok(new CommonResponseDto(true, "이메일을 전송하였습니다."));
    }*/

    
    @Operation(summary = "Access Token 재발급", description = "Access Token 재발급 API")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Access Token 재발급 성공", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ApiResponseDto.class))),
        @ApiResponse(responseCode = "401", description = "인증 실패",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
        @ApiResponse(responseCode = "500", description = "서버 오류",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponseDto<JwtResponseDto>> refreshToken(@RequestBody JwtRequestDto request) throws Exception {
        JwtResponseDto jwtResponse = refreshTokenService.verifyExpiration(request);
        return ResponseEntity.ok(ApiResponseDto.success(ErrorCode.SUCCESS, "access token을 재발급하였습니다.", jwtResponse));
    }
    
}
