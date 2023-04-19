package com.aptech.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.aptech.blog.model.Role;
import com.aptech.blog.model.UserRole;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(UserRole role);
}
