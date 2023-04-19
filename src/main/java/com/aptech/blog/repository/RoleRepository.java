package com.aptech.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.aptech.blog.model.Role;
import com.aptech.blog.model.UserRoles;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(UserRoles role);
}
