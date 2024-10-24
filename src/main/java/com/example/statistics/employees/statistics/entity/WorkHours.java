package com.example.statistics.employees.statistics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "work_hours")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkHours {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID employeeId;

    @Column(nullable = false)
    private UUID projectId;

    @Column(nullable = false)
    private BigDecimal workedHours;

    @Column(nullable = false)
    private BigDecimal overtime;

    @Column(nullable = false)
    private LocalDateTime createdAt;}

