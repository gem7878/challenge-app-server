package com.gem.project.note.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import com.gem.project.note.dto.CulturalEventResponseDto;
import com.gem.project.note.dto.CulturalEventApiDto;
import com.gem.project.note.entity.CulturalEvent;
import com.gem.project.note.entity.SORT;
import com.gem.project.note.repository.CulturalEventRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional
public class CulturalEventService {

    private final CulturalEventRepository culturalEventRepository;
    //private final RestTemplate restTemplate;
    //private static final String API_URL = "http://openapi.seoul.go.kr:8088/{apiKey}/json/SearchConcertDetailService/1/1000/";

    public CulturalEventResponseDto getAllEvent(int pageNumber, SORT sort) {
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort.getFiled()));
        Page<CulturalEvent> eventsPage = culturalEventRepository.findAll(pageRequest);
        return new CulturalEventResponseDto(eventsPage.getContent(), eventsPage.getTotalPages());
    }
/*
    @Scheduled(fixedRate = 43200000) // 12시간마다 실행 (12 * 60 * 60 * 1000 ms)
    public void updateCulturalEvents() {
        String apiKey = System.getenv("SEOUL_API_KEY"); // 환경 변수에서 API 키 가져오기
        String url = API_URL.replace("{apiKey}", apiKey);
        
        try {
            CulturalEventApiDto[] response = restTemplate.getForObject(url, CulturalEventApiDto[].class);
            if (response != null) {
                List<CulturalEvent> events = Arrays.stream(response)
                    .map(this::convertToEntity)
                    .toList();
                
                // 기존 데이터 삭제 후 새로운 데이터 저장
                culturalEventRepository.deleteAll();
                culturalEventRepository.saveAll(events);
            }
        } catch (Exception e) {
            // 로그 처리
            e.printStackTrace();
        }
    }

    private CulturalEvent convertToEntity(CulturalEventApiDto dto) {
        return CulturalEvent.builder()
            .title(dto.getTITLE())
            .category(CulturalEventCategory.fromString(dto.getCODENAME()))
            .district(dto.getGUNAME())
            .place(dto.getPLACE())
            .startDate(dto.getSTRTDATE())
            .endDate(dto.getEND_DATE())
            .target(dto.getUSE_TRGT())
            .fee(dto.getUSE_FEE())
            .player(dto.getPLAYER())
            .program(dto.getPROGRAM())
            .description(dto.getETC_DESC())
            .homepage(dto.getORG_LINK())
            .imageUrl(dto.getMAIN_IMG())
            .ticket(dto.getTICKET())
            .build();
    } */
}
