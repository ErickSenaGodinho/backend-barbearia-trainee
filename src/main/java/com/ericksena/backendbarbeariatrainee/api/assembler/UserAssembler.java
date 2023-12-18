package com.ericksena.backendbarbeariatrainee.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ericksena.backendbarbeariatrainee.api.model.UserDTO;
import com.ericksena.backendbarbeariatrainee.domain.model.User;

@Component
public class UserAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toModel(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
