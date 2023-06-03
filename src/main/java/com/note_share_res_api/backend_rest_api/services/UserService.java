package com.note_share_res_api.backend_rest_api.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.note_share_res_api.backend_rest_api.modesl.User;
import com.note_share_res_api.backend_rest_api.repository.UserRepository;

@Component
public class UserService {
    private UserRepository userRepo;

    UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

}
