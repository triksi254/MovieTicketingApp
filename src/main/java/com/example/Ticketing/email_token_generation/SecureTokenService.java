package com.example.ticketing.email_token_generation;

import com.example.ticketing.model.SecureToken;

public interface SecureTokenService {

    SecureToken createSecureToken();

    void saveSecureToken(SecureToken token);

    void removeToken(SecureToken token);

    SecureToken findByToken(String token);
}
