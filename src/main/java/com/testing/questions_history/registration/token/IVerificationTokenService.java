package com.testing.questions_history.registration.token;

import com.testing.questions_history.user.User;

import java.util.Optional;

public interface IVerificationTokenService {

    String validateToken(String token);

    void saveVerificationTokenForUser(User user, String token);

    Optional<VerificationToken> findByToken(String token);

    void deleteUserToken(Long id);
}
