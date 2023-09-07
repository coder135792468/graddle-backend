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
            @RequestParam(required = false, defaultValue = "-1") String ownerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestParam(required = false, defaultValue = "") String search) {
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
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Note> notesPage;
            if (search.length() > 0) {
                notesPage = noteService.searchNotes(search.toLowerCase(), pagingSort);
            } else if (ownerId == "-1" || search.length() == 0)
                notesPage = noteService.findAll(pagingSort);
            else
                notesPage = noteService.findByUid(ownerId, pagingSort);

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

    @CrossOrigin
    @GetMapping(value = "/notes/{postId}")
    public ResponseEntity<Object> findNoteById(@PathVariable int postId) {
        try {
            Object note = noteService.findById(postId).get();
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin
    @GetMapping(value = "/notes/owner/{ownerId}")
    public ResponseEntity<Integer> getUserDownloadCount(@PathVariable String ownerId) {
        try {
            return new ResponseEntity<>(noteService.getDownloadCount(ownerId).stream().map(a -> a.download)
                    .mapToInt(Integer::intValue).sum(), HttpStatus.OK);
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
