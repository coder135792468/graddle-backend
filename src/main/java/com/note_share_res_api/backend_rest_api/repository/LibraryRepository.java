package com.note_share_res_api.backend_rest_api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import com.note_share_res_api.backend_rest_api.modesl.*;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
    Page<Library> findByUid(String ownerId, Pageable pageable);

    @Query(value = "SELECT * FROM Library l where l.cur_note_id = :curNoteId", nativeQuery = true)
    List<Library> getLibaryNote(@PathVariable Integer curNoteId);
}