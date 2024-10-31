package com.example.statistics.employees.statistics.controller;

import com.example.statistics.employees.statistics.dto.UserDto;
import com.example.statistics.employees.statistics.dto.UserWorkHoursDto;
import com.example.statistics.employees.statistics.repository.WorkHoursRepository;
import com.example.statistics.employees.statistics.serviceImpl.StatisticsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StatisticsControllerIntegrationTest {

    private MockMvc mockMvc;


    @Autowired
    private WorkHoursRepository workHoursRepository;
    @MockBean
    private StatisticsServiceImpl statisticsService;

    @Autowired
    private StatisticsController statisticsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();
    }

    @Test
    void calculateOvertime() throws Exception {
        when(statisticsService.calculateOvertime(1L)).thenReturn(new BigDecimal("5.0"));

        mockMvc.perform(get("/statistics/overtime")
                        .param("employeeId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(5.0));
    }

    @Test
    void calculateBonuses() throws Exception {
        when(statisticsService.calculateOvertimeBonuses(1L)).thenReturn(new BigDecimal("50.0"));

        mockMvc.perform(get("/statistics/bonuses")
                        .param("employeeId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(50.0));
    }

    @Test
    void getProjectStatistics() throws Exception {
        when(statisticsService.getProjectStatistics(1L)).thenReturn(Map.of(
                "totalWorkedHours", new BigDecimal("100.0"),
                "totalEmployees", 5
        ));

        mockMvc.perform(get("/statistics/projects")
                        .param("projectId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalWorkedHours").value(100.0))
                .andExpect(jsonPath("$.totalEmployees").value(5));
    }

    @Test
    void getWorkHoursForUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserId(1L);

        UserWorkHoursDto userWorkHoursDto = new UserWorkHoursDto(userDto, List.of());

        when(statisticsService.getWorkHoursForUser(any(UserDto.class))).thenReturn(userWorkHoursDto);

        mockMvc.perform(post("/statistics/workhours")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.userId").value(1)); // Change 'userDto' to 'user'


    }
}
