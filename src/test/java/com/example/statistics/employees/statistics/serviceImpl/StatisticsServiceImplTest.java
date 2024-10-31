package com.example.statistics.employees.statistics.serviceImpl;

import com.example.statistics.employees.statistics.dto.UserDto;
import com.example.statistics.employees.statistics.dto.UserWorkHoursDto;
import com.example.statistics.employees.statistics.entity.WorkHours;
import com.example.statistics.employees.statistics.repository.WorkHoursRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StatisticsServiceImplTest {

    @Mock
    private WorkHoursRepository workHoursRepository;

    @InjectMocks
    private StatisticsServiceImpl statisticsServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateOvertime() {
        WorkHours workHours = new WorkHours();
        workHours.setOvertime(new BigDecimal("5"));
        when(workHoursRepository.findAllByEmployeeId(1L)).thenReturn(Collections.singletonList(workHours));

        BigDecimal result = statisticsServiceImpl.calculateOvertime(1L);
        assertEquals(new BigDecimal("5"), result);
    }

    @Test
    void calculateOvertimeBonuses() {
        WorkHours workHours = new WorkHours();
        workHours.setOvertime(new BigDecimal("5"));
        when(workHoursRepository.findAllByEmployeeId(1L)).thenReturn(Collections.singletonList(workHours));

        BigDecimal result = statisticsServiceImpl.calculateOvertimeBonuses(1L);
        assertEquals(new BigDecimal("50"), result);  // 5 * 10 = 50
    }

    @SneakyThrows
    @Test
    void getProjectStatistics() {
        WorkHours workHours = new WorkHours();
        workHours.setWorkedHours(new BigDecimal("8"));
        workHours.setEmployeeId(1L);
        when(workHoursRepository.findAllByProjectId(1L)).thenReturn(List.of(workHours, workHours));

        Object result = statisticsServiceImpl.getProjectStatistics(1L);
        BigDecimal totalWorkedHours = ((BigDecimal) result.getClass().getMethod("getTotalWorkedHours").invoke(result));
        int totalEmployees = ((int) result.getClass().getMethod("getTotalEmployees").invoke(result));

        assertEquals(new BigDecimal("16"), totalWorkedHours);
        assertEquals(1, totalEmployees);
    }

    @Test
    void getWorkHoursForUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1L);
        WorkHours workHours = new WorkHours();
        when(workHoursRepository.findAllByEmployeeId(1L)).thenReturn(List.of(workHours));

        UserWorkHoursDto result = (UserWorkHoursDto) statisticsServiceImpl.getWorkHoursForUser(userDto);
        assertEquals(userDto.getUserId(), result.getUser().getUserId());
        assertEquals(1, result.getWorkHours().size());
    }
}
