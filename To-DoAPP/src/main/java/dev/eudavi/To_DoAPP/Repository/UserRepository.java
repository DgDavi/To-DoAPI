package dev.eudavi.To_DoAPP.Repository;

import dev.eudavi.To_DoAPP.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
}
