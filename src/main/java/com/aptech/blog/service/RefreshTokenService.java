package com.aptech.blog.service;

import java.util.Optional;

import com.aptech.blog.model.RefreshToken;

public interface RefreshTokenService {
    public Optional<RefreshToken> findByToken(String token);
    public RefreshToken createRefreshToken(String email);
    public RefreshToken verifyExpiration(RefreshToken token);
}
