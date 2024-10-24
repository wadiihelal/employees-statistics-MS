package com.example.statistics.employees.statistics.service;

import com.example.statistics.employees.statistics.dto.UserDto;

import java.math.BigDecimal;
import java.util.UUID;

public interface StatisticsService {

    BigDecimal calculateOvertime(UUID employeeId);

    BigDecimal calculateOvertimeBonuses(UUID employeeId);

    Object getProjectStatistics(UUID projectId);

    Object getWorkHoursForUser(UserDto userDto);
}
