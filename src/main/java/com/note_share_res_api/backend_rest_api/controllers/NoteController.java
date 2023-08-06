package com.note_share_res_api.backend_rest_api.controllers;

import java.util.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.note_share_res_api.backend_rest_api.modesl.*;
import com.note_share_res_api.backend_rest_api.repository.*;

@RestController
public class NoteController {

    NoteRepository noteService;
    UserRepository userService;

    NoteController(NoteRepository noteService, UserRepository userService) {
        this.noteService = noteService;
        this.userService = userService;
    }
    
    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
          return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
          return Sort.Direction.DESC;
        }
    
        return Sort.Direction.ASC;
      }
    @CrossOrigin
    @GetMapping(value = "/notes")
    public ResponseEntity<Map<String, Object>> getAllNotes(
            @RequestParam(required = false, defaultValue = "-1") int ownerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (sort[0].contains(",")) {
              for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
              }
            } else {
              orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Note> notes = new ArrayList<Note>();
            Pageable pagingSort = PageRequest.of(page, size,Sort.by(orders));

            Page<Note> notesPage;
            if (ownerId == -1)
                notesPage = noteService.findAll(pagingSort);
            else
                notesPage = noteService.findByOwnerId(ownerId, pagingSort);

            notes = notesPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("notes", notes);
            response.put("currentPage", notesPage.getNumber());
            response.put("totalItems", notesPage.getTotalElements());
            response.put("totalPages", notesPage.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     @PostMapping(value = "/notes/like/{postId}")
    public ResponseEntity<Note> likePost(@PathVariable int postId, @RequestBody Integer userId) {
        try {
            Optional<Note> note = noteService.findById(postId);
            Optional<User> user = userService.findById(userId);
            if (note.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            Note curNote = note.get();
            List<User> likes = curNote.getLikes();
            likes.add(user.get());
            curNote.setLikes(likes);

            curNote = noteService.save(curNote);

            return new ResponseEntity<Note>(curNote, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/notes/{id}")
    public ResponseEntity<Note> addNote(@PathVariable int id, @RequestBody Note noteData) {
        try {
            Optional<User> user = userService.findById(id);
            noteData.setUser(user.get());
            Note note = noteService.save(noteData);
            return new ResponseEntity<>(note, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/notes/{postId}")
    public ResponseEntity<Note> updatePost(@PathVariable int postId, @RequestBody Note noteData) {
        try {
            Optional<Note> curNote = noteService.findById(postId);
            if (curNote.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            Note note = curNote.get();
            note.setContent(noteData.getContent());
            note = noteService.save(note);
            return new ResponseEntity<Note>(note, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/notes/{postId}")
    public ResponseEntity<String> deleteNote(@PathVariable int postId) {
        try {
            Optional<Note> note = noteService.findById(postId);
            if (note.isEmpty()) {
                return new ResponseEntity<>("Post doesn't exits", HttpStatus.BAD_REQUEST);
            }

            noteService.deleteById(postId);
            return new ResponseEntity<>("Sucessfully Deleted Note with id" + postId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

   

}
