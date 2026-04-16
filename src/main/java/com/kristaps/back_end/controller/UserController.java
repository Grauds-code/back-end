package com.kristaps.back_end.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kristaps.back_end.models.UserModel;
import com.kristaps.back_end.services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/api/v2/users")
    public ResponseEntity<Long> createUser(@RequestBody UserModel user) {
        Long userId = userService.createUser(user);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @GetMapping("/api/v2/users/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.existsByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/api/v2/users")
    public ResponseEntity<Long> getUserIdByEmail(@RequestParam String email, @RequestParam String password) {
        Long id = userService.findByEmailAndPassword(email, password);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
