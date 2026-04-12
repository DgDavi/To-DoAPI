package dev.eudavi.To_DoAPP.Service;

import dev.eudavi.To_DoAPP.DTO.TaskDTO;
import dev.eudavi.To_DoAPP.Model.TaskModel;
import dev.eudavi.To_DoAPP.Repository.TaskRepository;
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
