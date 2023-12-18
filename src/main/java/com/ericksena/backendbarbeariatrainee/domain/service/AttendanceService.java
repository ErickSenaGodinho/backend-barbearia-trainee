package com.ericksena.backendbarbeariatrainee.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericksena.backendbarbeariatrainee.domain.exceptions.AttendanceNotFoundException;
import com.ericksena.backendbarbeariatrainee.domain.model.Attendance;
import com.ericksena.backendbarbeariatrainee.domain.model.Customer;
import com.ericksena.backendbarbeariatrainee.domain.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private CustomerService customerService;

    public Attendance findById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException());
    }

    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    @Transactional
    public Attendance save(Attendance attendance) {
        Customer customer = customerService.findById(attendance.getCustomer().getId());
        attendance.setCustomer(customer);
        return attendanceRepository.save(attendance);
    }

    public Attendance update(Attendance attendance) {
        if (!attendanceRepository.existsById(attendance.getId())) {
            throw new AttendanceNotFoundException();
        }
        return save(attendance);
    }

    public void deleteById(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new AttendanceNotFoundException();
        }
        attendanceRepository.deleteById(id);
    }

}
