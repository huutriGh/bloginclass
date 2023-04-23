package com.aptech.blog.service;

import com.aptech.blog.dto.LoginDto;
import com.aptech.blog.dto.UserDto;

public interface UserService {
    UserDto signup(UserDto user);

    UserDto login(LoginDto loginDto);

    UserDto findUserByEmail(String email);
}
