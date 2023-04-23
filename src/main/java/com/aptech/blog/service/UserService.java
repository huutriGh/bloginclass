package com.aptech.blog.service;

import java.util.List;

import com.aptech.blog.dto.LoginRequest;
import com.aptech.blog.dto.UserDto;
import com.aptech.blog.model.User;

public interface UserService {
    UserDto signup(UserDto user);

    UserDto login(LoginRequest loginDto);

    UserDto findUserByEmail(String email);

    List<User> getAllUser();
}
