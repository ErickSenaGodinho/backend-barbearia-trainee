package com.ericksena.backendbarbeariatrainee.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegisterDTO {
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String phone;
}
