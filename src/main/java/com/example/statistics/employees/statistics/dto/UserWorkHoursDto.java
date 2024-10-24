package com.example.statistics.employees.statistics.dto;

import com.example.statistics.employees.statistics.entity.WorkHours;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserWorkHoursDto {
    private UserDto user;
    private List<WorkHours> workHours;
}
