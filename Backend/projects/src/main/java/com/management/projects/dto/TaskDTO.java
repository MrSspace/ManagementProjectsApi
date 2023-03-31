package com.management.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private NameEmail manager;

}
