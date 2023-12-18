package com.ericksena.backendbarbeariatrainee.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ericksena.backendbarbeariatrainee.domain.model.User;
import com.ericksena.backendbarbeariatrainee.domain.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).stream().findFirst().orElse(null);
    }

    public User create(User user) {
        User foundUser = findByEmail(user.getEmail());
        if (foundUser != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used");
        }
        return userRepository.save(user);
    }

    public User update(User user) {
        User foundUser = findByEmail(user.getEmail());
        if (foundUser != null && user.getId() != foundUser.getId()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used");
        }
        return userRepository.save(user);
    }

}
