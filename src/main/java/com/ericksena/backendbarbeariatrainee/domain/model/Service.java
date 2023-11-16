package com.ericksena.backendbarbeariatrainee.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Valid
    @ManyToOne
    private Customer customer;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

}
