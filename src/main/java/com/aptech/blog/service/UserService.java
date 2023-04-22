package com.aptech.blog.service;

import com.aptech.blog.dto.LoginRequest;
import com.aptech.blog.dto.UserDto;

public interface UserService {
    UserDto signup(UserDto user);

    UserDto login(LoginRequest loginDto);

    UserDto findUserByEmail(String email);
}
