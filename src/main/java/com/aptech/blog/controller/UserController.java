package com.aptech.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aptech.blog.dto.LoginDto;
import com.aptech.blog.dto.UserDto;
import com.aptech.blog.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        UserDto userDto = userService.login(loginDto);
        if (userDto.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or password");
        }
        return ResponseEntity.ok(userDto);
    }

}
