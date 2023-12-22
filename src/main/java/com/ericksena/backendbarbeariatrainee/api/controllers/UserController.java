package com.ericksena.backendbarbeariatrainee.api.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ericksena.backendbarbeariatrainee.api.assembler.UserAssembler;
import com.ericksena.backendbarbeariatrainee.api.model.UserDTO;
import com.ericksena.backendbarbeariatrainee.api.model.UserLoginDTO;
import com.ericksena.backendbarbeariatrainee.domain.model.User;
import com.ericksena.backendbarbeariatrainee.domain.service.UserService;

@RestController()
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAssembler userAssembler;

    @PostMapping()
    public UserDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        User user = userService.findByAuth(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        UserDTO userDTO = userAssembler.toModel(user);
        String credential = Base64.getEncoder()
                .encodeToString((userLoginDTO.getEmail() + ":" + userLoginDTO.getPassword()).getBytes());
        userDTO.setCredential(credential);
        return userDTO;
    }

    @PutMapping(value = "/{id}")
    public UserDTO update(@RequestHeader("Authorization") String authHeader, @PathVariable Long id,
            @RequestBody User user) {
        user.setId(id);
        User updatedUser = userService.update(user, authHeader);
        UserDTO userDTO = userAssembler.toModel(updatedUser);
        String credential = Base64.getEncoder().encodeToString(
                (user.getEmail() + ":" + user.getPassword()).getBytes());
        userDTO.setCredential(credential);
        return userDTO;
    }
}
