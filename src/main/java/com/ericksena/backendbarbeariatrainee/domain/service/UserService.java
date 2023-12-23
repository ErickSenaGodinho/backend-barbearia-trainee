package com.ericksena.backendbarbeariatrainee.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ericksena.backendbarbeariatrainee.domain.model.User;
import com.ericksena.backendbarbeariatrainee.domain.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).stream().findFirst().orElse(null);
    }

    public User create(User user) {
        User foundUser = findByEmail(user.getEmail());
        if (foundUser != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used");
        }
        encodePassword(user);
        return userRepository.save(user);
    }

    public User update(User user, String authHeader) {
        User foundUser = findByEmail(user.getEmail());
        if (foundUser != null && user.getId() != foundUser.getId()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used");
        }
        encodePassword(user);
        return userRepository.save(user);
    }

    public User findByAuth(String email, String password) {
        User foundUser = findByEmail(email);
        if (foundUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else if (!passwordEncoder.matches(password, foundUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        return foundUser;
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

}
