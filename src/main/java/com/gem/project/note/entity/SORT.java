package com.gem.project.note.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SORT {
    START_DATE("startDate"),
    END_DATE("endDate");

    private final String filed;
}
