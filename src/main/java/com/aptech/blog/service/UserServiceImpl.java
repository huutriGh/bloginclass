package com.aptech.blog.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aptech.blog.dto.LoginRequest;
import com.aptech.blog.dto.RoleDto;
import com.aptech.blog.dto.UserDto;
import com.aptech.blog.model.Role;
import com.aptech.blog.model.User;
import com.aptech.blog.model.UserRole;
import com.aptech.blog.repository.RoleRepository;
import com.aptech.blog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto signup(UserDto userDto) {

        Role userRole;
        User user = userRepository.findByEmail(userDto.getEmail()).get();
        if (user == null) {
            if (userDto.isAdmin()) {
                userRole = roleRepository.findByRole(UserRole.ADMIN);
            } else {
                userRole = roleRepository.findByRole(UserRole.USER);
            }

            user = new User().setEmail(userDto.getEmail())
                    .setPassword(passwordEncoder.encode(userDto.getPassword()))
                    .setRoles(Arrays.asList(userRole))
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setMobileNumber(userDto.getMobileNumber());
            userRepository.save(user);
            userDto.setPassword("");

        }
        return userDto;
    }

    @Override
    public UserDto findUserByEmail(String email) {

        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email).get());
        if (user.isPresent()) {
            return modelMapper.map((user.get()), UserDto.class);

        }

        return null;

    }

    @Override
    public UserDto login(LoginRequest loginDto) {
        User user = userRepository.findByEmail(loginDto.getUsername()).get();
        UserDto userDto = new UserDto();
        if (user != null) {
            Boolean checkPass = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
            if (checkPass) {
                List<RoleDto> roles = new ArrayList<>();
                user.getRoles().forEach(role -> {
                    RoleDto roleDto = new RoleDto().setRole(role.getRole().toString());
                    roles.add(roleDto);

                });
                userDto.setEmail(user.getEmail()).setFirstName(user.getFirstName())
                        .setLastName(user.getLastName())
                        .setMobileNumber(user.getMobileNumber())
                        .setRoles(roles);

                return userDto;
            }

        }

        return userDto;

    }

}
