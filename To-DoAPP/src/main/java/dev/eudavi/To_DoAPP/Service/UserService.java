package dev.eudavi.To_DoAPP.Service;

import dev.eudavi.To_DoAPP.DTO.UserDTO;
import dev.eudavi.To_DoAPP.Model.UserModel;
import dev.eudavi.To_DoAPP.Repository.UserRepository;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForArraysOfLong;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel salvar(UserDTO dto) {
        UserModel userModel = new UserModel();
        userModel.setName(dto.getName());
        userModel.setEmail(dto.getEmail());
        userModel.setPassword(dto.getPassword());

        return userRepository.save(userModel);
    }

    public UserModel login(String email, String password) {
        UserModel userModel = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if (!userModel.getPassword().equals(password)) {
            throw new RuntimeException("Incorrect password");

        }
        return userModel;
    }
}
