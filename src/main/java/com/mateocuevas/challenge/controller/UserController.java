package com.mateocuevas.challenge.controller;

import com.mateocuevas.challenge.dto.AuthResponse;
import com.mateocuevas.challenge.dto.LogInRequest;
import com.mateocuevas.challenge.dto.UserDto;
import com.mateocuevas.challenge.entity.User;
import com.mateocuevas.challenge.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/get")
    public ResponseEntity<UserDto> getUserDetails(){
        return ResponseEntity.ok(userService.getUser());
    }
}
