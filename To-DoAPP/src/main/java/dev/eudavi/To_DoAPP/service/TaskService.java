package dev.eudavi.To_DoAPP.service;

import dev.eudavi.To_DoAPP.dto.TaskDTO;
import dev.eudavi.To_DoAPP.model.TaskModel;
import dev.eudavi.To_DoAPP.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    public final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskModel salvar(TaskDTO dto) {
        TaskModel taskModel = new TaskModel();
        taskModel.setTitle(dto.getTitle());
        taskModel.setDescription(dto.getDescription());

        return taskRepository.save(taskModel);
    }


}
