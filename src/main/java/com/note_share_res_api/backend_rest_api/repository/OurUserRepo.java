package com.note_share_res_api.backend_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note_share_res_api.backend_rest_api.entity.OurUsers;

import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String email);
}