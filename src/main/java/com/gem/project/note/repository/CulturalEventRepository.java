package com.gem.project.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gem.project.note.entity.CulturalEvent;

public interface CulturalEventRepository extends JpaRepository<CulturalEvent, Long> {
}
