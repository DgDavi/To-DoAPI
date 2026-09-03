package dev.eudavi.To_DoAPP.repository;

import dev.eudavi.To_DoAPP.model.TaskModel;
import dev.eudavi.To_DoAPP.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel,Long> {
    List<TaskModel> findByUserOrderByIdAsc(UserModel user);
}
