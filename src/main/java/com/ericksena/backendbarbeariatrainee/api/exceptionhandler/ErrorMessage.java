package com.ericksena.backendbarbeariatrainee.api.exceptionhandler;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class ErrorMessage {
    private Integer status;
    private OffsetDateTime dateTime;
    private String reason;

}