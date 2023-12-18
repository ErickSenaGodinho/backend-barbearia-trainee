package com.ericksena.backendbarbeariatrainee.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private UserDTO user;
    private String cpf;
    private String phone;
}
