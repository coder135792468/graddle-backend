package com.note_share_res_api.backend_rest_api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.note_share_res_api.backend_rest_api.modesl.Note;
import com.note_share_res_api.backend_rest_api.repository.NoteRepository;

@RestController
public class NoteController {
     
    NoteRepository noteService;
    NoteController(NoteRepository noteService){
        this.noteService = noteService;
    }
    @GetMapping(value="/notes")
    public ResponseEntity<Map<String, Object>> getAllNotes( 
        @RequestParam(required = false,defaultValue = "-1") int ownerId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size
    ) {
        try{

            List<Note> notes = new ArrayList<Note>();
            Pageable pagingSort = PageRequest.of(page, size);

            Page<Note> notesPage;
            if(ownerId == -1)  notesPage = noteService.findAll(pagingSort);
            else notesPage = noteService.findByOwnerId(ownerId,pagingSort);

            notes = notesPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("notes", notes);
            response.put("currentPage", notesPage.getNumber());
            response.put("totalItems", notesPage.getTotalElements());
            response.put("totalPages", notesPage.getTotalPages());


            return new ResponseEntity<>(response, HttpStatus.OK);
        }   catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
    
}