package com.note_share_res_api.backend_rest_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.note_share_res_api.backend_rest_api.modesl.*;

public interface NoteRepository extends JpaRepository<Library, Integer> {

    Page<Library> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM Library n WHERE lower(n.items) LIKE CONCAT('%', :query, '%') AND n.college LIKE CONCAT('%',:institution, '%')", nativeQuery = true)
    Page<Library> findByInstitution(String institution, String query, Pageable pageable);

    @Query(value = "SELECT * FROM Library n WHERE lower(n.items) LIKE CONCAT('%', :query, '%')", nativeQuery = true)
    Page<Library> searchNotes(String query, Pageable pageable);
}
