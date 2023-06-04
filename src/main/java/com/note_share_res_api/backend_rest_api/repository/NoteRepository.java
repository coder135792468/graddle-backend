package com.note_share_res_api.backend_rest_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note_share_res_api.backend_rest_api.modesl.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    // @Query("SELECT note from notes where note.user.id = :id")
    Page<Note> findByOwnerId(int id, Pageable pageable);
}
