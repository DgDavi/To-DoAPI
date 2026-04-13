package dev.eudavi.To_DoAPP.repository;

import dev.eudavi.To_DoAPP.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskModel,String> {
    Optional<TaskModel> findByTitle(String title);
}
