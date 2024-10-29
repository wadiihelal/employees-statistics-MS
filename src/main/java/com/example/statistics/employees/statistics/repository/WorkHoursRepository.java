package com.example.statistics.employees.statistics.repository;

import com.example.statistics.employees.statistics.entity.WorkHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkHoursRepository extends JpaRepository<WorkHours, Long> {

    List<WorkHours> findAllByEmployeeId(Long employeeId);

    List<WorkHours> findAllByProjectId(Long projectId);
}
