package com.example.statistics.employees.statistics.service;

import com.example.statistics.employees.statistics.dto.UserDto;

import java.math.BigDecimal;

public interface StatisticsService {

    BigDecimal calculateOvertime(Long employeeId);

    BigDecimal calculateOvertimeBonuses(Long employeeId);

    Object getProjectStatistics(Long projectId);

    Object getWorkHoursForUser(UserDto userDto);
}
