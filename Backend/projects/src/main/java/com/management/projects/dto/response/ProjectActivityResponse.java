package com.management.projects.dto.response;

import com.management.projects.dto.NameEmail;
import com.management.projects.dto.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectActivityResponse extends TaskDTO {

    private List<NameEmail> collaborators;
    private List<Object> activitiesTasks;

}
