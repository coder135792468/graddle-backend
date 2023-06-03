package com.note_share_res_api.backend_rest_api.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.note_share_res_api.backend_rest_api.modesl.*;
import com.note_share_res_api.backend_rest_api.repository.*;

@Component
public class NoteService {
    private NoteRepository noteRepo;

    NoteService(NoteRepository noteRepo){
        this.noteRepo = noteRepo;
    }

    public List<Note> findAllNotes(){
        return noteRepo.findAll();
    }

}