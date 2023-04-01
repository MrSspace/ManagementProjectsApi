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
public class ProjectRequest {

    private String boardId;
    private String name;
    private String description;
    private NameEmail manager;

}
