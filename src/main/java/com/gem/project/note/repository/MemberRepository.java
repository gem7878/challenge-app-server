package com.gem.project.note.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gem.project.note.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    // Optional<MemberProjection> findByEmail(String email);
}
