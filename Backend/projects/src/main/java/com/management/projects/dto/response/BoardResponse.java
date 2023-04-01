package com.management.projects.dto.response;

import com.management.projects.dto.NameEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponse {

    private String id;
    private String name;
    private NameEmail owner;
    private List<ProjectResponse> projects;
    private List<NameEmail> collaborators;

}
