package com.example.statistics.employees.statistics.controller;

import com.example.statistics.employees.statistics.dto.UserDto;
import com.example.statistics.employees.statistics.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
    @GetMapping("/overtime")
    public BigDecimal calculateOvertime(@RequestParam UUID employeeId) {
        return statisticsService.calculateOvertime(employeeId);
    }
    @GetMapping("/bonuses")
    public BigDecimal calculateBonuses(@RequestParam UUID employeeId) {
        return statisticsService.calculateOvertimeBonuses(employeeId);
    }
    @GetMapping("/projects")
    public Object getProjectStatistics(@RequestParam UUID projectId) {
        return statisticsService.getProjectStatistics(projectId);
    }
    @PostMapping("/workhours")
    public Object getWorkHoursForUser(@RequestBody UserDto userDto) {
        return statisticsService.getWorkHoursForUser(userDto);
    }
}
