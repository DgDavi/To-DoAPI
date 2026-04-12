package dev.eudavi.To_DoAPP.Repository;

import dev.eudavi.To_DoAPP.Model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskModel,String> {
    Optional<TaskModel> findByTitle(String title);
}
