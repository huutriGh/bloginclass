package com.aptech.blog.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aptech.blog.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
