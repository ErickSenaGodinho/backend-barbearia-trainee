package com.ericksena.backendbarbeariatrainee.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AttendanceNotFoundException extends ResponseStatusException {

    public AttendanceNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Attendance Not Found");
    }

}
