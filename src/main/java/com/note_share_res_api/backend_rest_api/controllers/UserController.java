package com.note_share_res_api.backend_rest_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note_share_res_api.backend_rest_api.modesl.User;
import com.note_share_res_api.backend_rest_api.services.UserService;

@RestController
public class UserController {
     
    UserService userService;
    UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping(value="/users")
    public List<User> getAllUsers() {
        return  userService.findAllUsers();
    }
    
}
