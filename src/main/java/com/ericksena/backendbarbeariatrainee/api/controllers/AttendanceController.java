package com.ericksena.backendbarbeariatrainee.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ericksena.backendbarbeariatrainee.api.assembler.AttendanceAssembler;
import com.ericksena.backendbarbeariatrainee.api.model.AttendanceDTO;
import com.ericksena.backendbarbeariatrainee.domain.model.Attendance;
import com.ericksena.backendbarbeariatrainee.domain.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private AttendanceAssembler attendanceAssembler;

    @GetMapping()
    public List<AttendanceDTO> list() {
        return attendanceAssembler.toCollectionModel(attendanceService.findAll());
    }

    @GetMapping("/{id}")
    public AttendanceDTO findById(@PathVariable Long id) {
        Attendance foundAttendance = attendanceService.findById(id);
        return attendanceAssembler.toModel(foundAttendance);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AttendanceDTO create(@Valid @RequestBody AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceAssembler.toEntity(attendanceDTO);
        Attendance savedAttendance = attendanceService.save(attendance);
        return attendanceAssembler.toModel(savedAttendance);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AttendanceDTO update(@PathVariable Long id, @RequestBody AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceAssembler.toEntity(attendanceDTO);
        attendance.setId(id);
        Attendance updatedAttendance = attendanceService.update(attendance);
        return attendanceAssembler.toModel(updatedAttendance);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable Long id) {
        attendanceService.deleteById(id);
    }

}
