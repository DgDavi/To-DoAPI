package dev.eudavi.To_DoAPP.controller;

import dev.eudavi.To_DoAPP.dto.TaskDTO;
import dev.eudavi.To_DoAPP.dto.TaskResponseDTO;
import dev.eudavi.To_DoAPP.model.TaskModel;
import dev.eudavi.To_DoAPP.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskModel criarTask(@RequestBody @Valid TaskDTO dto, Authentication authentication) {
        String email = authentication.getName();
        return taskService.salvar(email, dto);
    }

    @GetMapping("/read")
    public List<TaskResponseDTO> exibirTarefas(Authentication authentication) {
        String email = authentication.getName();
        return taskService.exibirTarefas(email)
                .stream()
                .map(TaskResponseDTO::toDTO)
                .toList();
    }

}
