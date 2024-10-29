package com.example.statistics.employees.statistics.serviceImpl;

import com.example.statistics.employees.statistics.dto.UserDto;
import com.example.statistics.employees.statistics.dto.UserWorkHoursDto;
import com.example.statistics.employees.statistics.entity.WorkHours;
import com.example.statistics.employees.statistics.repository.WorkHoursRepository;
import com.example.statistics.employees.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private  WorkHoursRepository workHoursRepository;

    @Autowired
    public void StatisticsService(WorkHoursRepository workHoursRepository) {
        this.workHoursRepository = workHoursRepository;
    }

    @Override
    public BigDecimal calculateOvertime(Long employeeId) {
        List<WorkHours> workHoursList = workHoursRepository.findAllByEmployeeId(employeeId);
        return workHoursList.stream()
                .map(WorkHours::getOvertime)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal calculateOvertimeBonuses(Long employeeId) {
        BigDecimal overtime = calculateOvertime(employeeId);
        BigDecimal bonusRate = new BigDecimal("10");
        return overtime.multiply(bonusRate);
    }

    @Override
    public Object getProjectStatistics(Long projectId) {
        List<WorkHours> workHoursList = workHoursRepository.findAllByProjectId(projectId);
        BigDecimal totalWorkedHours = workHoursList.stream()
                .map(WorkHours::getWorkedHours)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Object() {
            public BigDecimal getTotalWorkedHours() {
                return totalWorkedHours;
            }
            public int getTotalEmployees() {
                return (int) workHoursList.stream().map(WorkHours::getEmployeeId).distinct().count();
            }
        };
    }

    @Override
    public Object getWorkHoursForUser(UserDto userDto) {
       List<WorkHours>  workHours = workHoursRepository.findAllByEmployeeId(userDto.getUserId());
        return new UserWorkHoursDto(userDto, workHours);
    }
}
