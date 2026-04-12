package dev.eudavi.To_DoAPP.Controller;

import dev.eudavi.To_DoAPP.DTO.TaskDTO;
import dev.eudavi.To_DoAPP.Model.TaskModel;
import dev.eudavi.To_DoAPP.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskModel criarTask(@RequestBody @Valid TaskDTO dto) {
        return taskService.salvar(dto);
    }
}
