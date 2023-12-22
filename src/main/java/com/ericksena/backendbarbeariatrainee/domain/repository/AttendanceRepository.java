package com.ericksena.backendbarbeariatrainee.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ericksena.backendbarbeariatrainee.domain.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query(value = "select * from attendance where start_time between :startDate and :endDate", nativeQuery = true)
    List<Attendance> findByDateInterval(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
