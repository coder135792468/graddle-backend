package com.note_share_res_api.backend_rest_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note_share_res_api.backend_rest_api.modesl.Note;
import com.note_share_res_api.backend_rest_api.services.NoteService;

@RestController
public class NoteController {
     
    NoteService noteService;
    NoteController(NoteService noteService){
        this.noteService = noteService;
    }
    @GetMapping(value="/notes")
    public List<Note> getAllNotes() {
        return  noteService.findAllNotes();
    }
    
}