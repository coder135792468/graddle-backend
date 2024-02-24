package com.note_share_res_api.backend_rest_api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note_share_res_api.backend_rest_api.modesl.*;

public interface NoteRepository extends JpaRepository<Library, Integer> {

    Optional<Library> findById(Integer id);

    Page<Library> findByCollegeAndCourse(String college, String course, Pageable pageable);

    Page<Library> findByCollege(String college, Pageable pageable);

    Page<Library> findByCourse(String query, Pageable pageable);

    Library save(Optional<Library> curLibrary);
}
