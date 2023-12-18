package com.ericksena.backendbarbeariatrainee.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ericksena.backendbarbeariatrainee.domain.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
