package com.note_share_res_api.backend_rest_api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.note_share_res_api.backend_rest_api.modesl.*;
import com.note_share_res_api.backend_rest_api.repository.LibraryRepository;
import com.note_share_res_api.backend_rest_api.repository.NoteRepository;

@RestController
public class LibraryController {

    LibraryRepository libraryService;
    NoteRepository noteService;

    LibraryController(LibraryRepository libraryService, NoteRepository noteService) {
        this.libraryService = libraryService;
        this.noteService = noteService;
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
    @GetMapping(value = "/library/{userId}")
    public ResponseEntity<Object> findLibraryByuserId(@PathVariable String userId,
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

            List<Library> library = new ArrayList<Library>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Library> libraryPage = libraryService.findByUid(userId, pagingSort);
            library = libraryPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("notes", library);
            response.put("currentPage", libraryPage.getNumber());
            response.put("totalItems", libraryPage.getTotalElements());
            response.put("totalPages", libraryPage.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin
    @PostMapping(value = "/library")
    public ResponseEntity<Object> saveLibraryNote(@RequestParam(defaultValue = "-1") int noteId,
            @RequestParam(defaultValue = "-1") String userId) {
        try {
            Optional<Note> note = noteService.findById(noteId);
            if (note.isEmpty()) {
                return new ResponseEntity<>("Note doesn't exits", HttpStatus.BAD_REQUEST);
            }
            Library library = new Library();
            library.setNote(note.get());
            library.setUid(userId);
            Library currData = libraryService.save(library);
            System.out.print(currData);
            return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @DeleteMapping(value = "/library/{libraryId}")
    public ResponseEntity<String> deleteLibraryNote(@PathVariable int libraryId,
            @RequestParam(defaultValue = "-1") String userId) {
        try {
            Optional<Library> library = libraryService.findById(libraryId);
            if (library.isEmpty()) {
                return new ResponseEntity<>("Note has been deleted", HttpStatus.BAD_REQUEST);
            }

            System.out.print(library.get().getUid());
            if (!library.get().getUid().equals(userId)) {
                return new ResponseEntity<>("Credential doesn't match", HttpStatus.BAD_REQUEST);
            }

            libraryService.deleteById(libraryId);
            return new ResponseEntity<>("Sucessfully Deleted Note with id " + libraryId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
