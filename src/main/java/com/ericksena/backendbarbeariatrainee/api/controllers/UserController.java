package com.ericksena.backendbarbeariatrainee.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ericksena.backendbarbeariatrainee.api.assembler.UserAssembler;
import com.ericksena.backendbarbeariatrainee.api.model.UserDTO;
import com.ericksena.backendbarbeariatrainee.domain.model.User;
import com.ericksena.backendbarbeariatrainee.domain.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAssembler userAssembler;

    @PutMapping(value = "/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User updatedUser = userService.update(user);
        return userAssembler.toModel(updatedUser);
    }
}
