package com.kristaps.back_end.services;

import org.springframework.stereotype.Service;

import com.kristaps.back_end.models.UserModel;
import com.kristaps.back_end.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(UserModel user) {
        UserModel saved = userRepository.save(user);
        return saved.getId();
    }

    public Long findByEmailAndPassword(String email, String password) {
        UserModel user = userRepository.findByEmailAndPassword(email, password);
        return user != null ? user.getId() : null;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
