package com.ericksena.backendbarbeariatrainee.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String credential;
}
