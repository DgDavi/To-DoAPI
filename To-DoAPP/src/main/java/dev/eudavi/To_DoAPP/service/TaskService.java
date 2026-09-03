package dev.eudavi.To_DoAPP.service;

import dev.eudavi.To_DoAPP.dto.TaskDTO;
import dev.eudavi.To_DoAPP.exception.TaskNotFoundException;
import dev.eudavi.To_DoAPP.model.TaskModel;
import dev.eudavi.To_DoAPP.model.UserModel;
import dev.eudavi.To_DoAPP.repository.TaskRepository;
import dev.eudavi.To_DoAPP.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    public final TaskRepository taskRepository;
    public final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskModel salvar(String email, TaskDTO dto) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with user email: " + email));

        TaskModel taskModel = new TaskModel();
        taskModel.setTitle(dto.getTitle());
        taskModel.setDescription(dto.getDescription());
        taskModel.setUser(user);

        return taskRepository.save(taskModel);
    }

    public List<TaskModel> exibirTarefas(String email) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with user email: " + email));

        List<TaskModel> tasks = taskRepository.findByUserOrderByIdAsc(user);

        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found for user: " + email);
        }

        return tasks;
    }

}
