package com.ericksena.backendbarbeariatrainee.api.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean performed;
}
