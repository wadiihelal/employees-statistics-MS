package com.example.statistics.employees.statistics.repository;

import com.example.statistics.employees.statistics.entity.WorkHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkHoursRepository extends JpaRepository<WorkHours, UUID> {

    List<WorkHours> findAllByEmployeeId(UUID employeeId);

    List<WorkHours> findAllByProjectId(UUID projectId);
}
