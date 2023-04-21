package com.aptech.blog.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class UserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private boolean isAdmin;
    private List<RoleDto> roles;
    
    

}