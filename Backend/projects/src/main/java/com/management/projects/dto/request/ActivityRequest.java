package com.management.projects.dto.request;

import com.management.projects.dto.NameEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityRequest {

    private String boardId;
    private String projectId;
    private String name;
    private String description;
    private NameEmail manager;
    private String startDate;
    private String endDate;

}
