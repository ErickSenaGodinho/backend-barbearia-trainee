package com.ericksena.backendbarbeariatrainee.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    String email;
    String password;
}
