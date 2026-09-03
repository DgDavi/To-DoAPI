package dev.eudavi.To_DoAPP.dto;

import dev.eudavi.To_DoAPP.model.TaskModel;

public class TaskResponseDTO {
    Long id;
    String title;
    String description;

    public TaskResponseDTO(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getTitle() { return title; }

    public Long getId() { return id; }

    public String getDescription() { return description; }

    public static TaskResponseDTO toDTO(TaskModel taskModel) {
        return new TaskResponseDTO(
                taskModel.getId(),
                taskModel.getTitle(),
                taskModel.getDescription()
        );
    }
}
