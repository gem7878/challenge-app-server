package com.gem.project.note.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gem.project.note.dto.ApiResponseDto;
import com.gem.project.note.dto.CulturalEventResponseDto;
import com.gem.project.note.dto.ErrorResponseDto;
import com.gem.project.note.entity.SORT;
import com.gem.project.note.exception.ErrorCode;
import com.gem.project.note.service.CulturalEventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cultural-event")
public class CulturalEventController {

    private final CulturalEventService eventService;

    /**
     * 문화 생활 목록 가져오기
     */

    @Operation(summary = "문화 생활 목록 가져오기")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "문화 생활 목록 가져오기 성공", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ApiResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
        @ApiResponse(responseCode = "500", description = "서버 오류",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    @GetMapping("")
    public ResponseEntity<ApiResponseDto<CulturalEventResponseDto>> getAllEvent(@RequestParam(name = "page") int pageNumber,
                                                                    @RequestParam(name = "sort") SORT sort) {
        CulturalEventResponseDto response = eventService.getAllEvent(pageNumber, sort);
        return ResponseEntity.ok(ApiResponseDto.success(ErrorCode.SUCCESS, "문화 생활 목록 가져오기 성공", response));
    }
}
