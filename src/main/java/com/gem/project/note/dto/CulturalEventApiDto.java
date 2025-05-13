package com.gem.project.note.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CulturalEventApiDto {
    private String CODENAME;        // 분류
    private String GUNAME;          // 구분
    private String TITLE;           // 제목
    private String DATE;            // 날짜
    private String PLACE;           // 장소
    private String ORG_NAME;        // 기관명
    private String USE_TRGT;        // 이용대상
    private String USE_FEE;         // 이용요금
    private String PLAYER;          // 출연자
    private String PROGRAM;         // 프로그램
    private String ETC_DESC;        // 기타설명
    private String ORG_LINK;        // 홈페이지
    private String MAIN_IMG;        // 대표이미지
    private String RGSTDATE;        // 등록일
    private String TICKET;          // 예매처
    private String STRTDATE;        // 시작일
    private String END_DATE;        // 종료일
    private String THEMECODE;       // 테마코드
} 