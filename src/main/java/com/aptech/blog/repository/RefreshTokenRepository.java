package com.aptech.blog.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aptech.blog.model.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
}
