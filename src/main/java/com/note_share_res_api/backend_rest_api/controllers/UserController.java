package com.note_share_res_api.backend_rest_api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.note_share_res_api.backend_rest_api.modesl.*;
import com.note_share_res_api.backend_rest_api.repository.UserRepository;

@RestController
public class UserController {
     
    UserRepository userService;
    UserController(UserRepository userService){
        this.userService = userService;
    }
    @GetMapping(value="/users")
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        try {

            List<User> notes = new ArrayList<User>();
            Pageable pagingSort = PageRequest.of(page, size);

            Page<User> notesPage = userService.findAll(pagingSort);

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
    
}
