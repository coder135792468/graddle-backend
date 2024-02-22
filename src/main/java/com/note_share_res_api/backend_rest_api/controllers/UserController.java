package com.note_share_res_api.backend_rest_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.note_share_res_api.backend_rest_api.dto.AuthRequest;
import com.note_share_res_api.backend_rest_api.dto.LoginResponse;
import com.note_share_res_api.backend_rest_api.dto.UserInfo;
import com.note_share_res_api.backend_rest_api.service.JwtService;
import com.note_share_res_api.backend_rest_api.service.UserInfoService;

// import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @CrossOrigin
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to note share";
    }

    @CrossOrigin
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userInfoService.addUser(userInfo));

    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> addUser(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            LoginResponse login = new LoginResponse(jwtService.generateToken(authRequest.getUserName()));

            return ResponseEntity.ok(login);
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    // @GetMapping("/getUsers")
    // @PreAuthorize("hasAuthority('ADMIN_ROLES')")
    // public List<UserInfo> getAllUsers() {
    // return userInfoService.getAllUser();
    // }

    // @GetMapping("/getUsers/{id}")
    // @PreAuthorize("hasAuthority('USER_ROLES')")
    // public UserInfo getAllUsers(@PathVariable Integer id) {
    // return userInfoService.getUser(id);
    // }
}
