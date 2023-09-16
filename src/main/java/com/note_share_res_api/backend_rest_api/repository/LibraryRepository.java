package com.note_share_res_api.backend_rest_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.note_share_res_api.backend_rest_api.modesl.*;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
    Page<Library> findByUid(String ownerId, Pageable pageable);

}