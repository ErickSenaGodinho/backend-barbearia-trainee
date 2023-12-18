package com.ericksena.backendbarbeariatrainee.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ericksena.backendbarbeariatrainee.api.model.AttendanceDTO;
import com.ericksena.backendbarbeariatrainee.domain.model.Attendance;

@Component
public class AttendanceAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AttendanceDTO toModel(Attendance attendance) {
        return modelMapper.map(attendance, AttendanceDTO.class);
    }

    public List<AttendanceDTO> toCollectionModel(List<Attendance> attendances) {
        return attendances.stream()
                .map(this::toModel)
                .toList();
    }

    public Attendance toEntity(AttendanceDTO attendanceDTO) {
        return modelMapper.map(attendanceDTO, Attendance.class);
    }
}
