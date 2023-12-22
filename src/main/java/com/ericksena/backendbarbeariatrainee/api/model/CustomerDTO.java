package com.ericksena.backendbarbeariatrainee.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private String credential;
    private String cpf;
    private String phone;
}
