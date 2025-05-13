package com.gem.project.note.dto;

import com.gem.project.note.entity.CulturalEvent;
import java.util.List;

public record CulturalEventResponseDto(
    List<CulturalEvent> events,
    int totalPages
) {}
