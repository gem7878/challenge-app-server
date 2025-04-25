package com.gem.project.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gem.project.note.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByToken(String token);
    void deleteByToken(String token);
}
