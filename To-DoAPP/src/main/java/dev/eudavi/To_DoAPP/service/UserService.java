package dev.eudavi.To_DoAPP.service;

import dev.eudavi.To_DoAPP.dto.UserDTO;
import dev.eudavi.To_DoAPP.exception.EmailAlreadyExistsException;
import dev.eudavi.To_DoAPP.exception.InvalidPasswordException;
import dev.eudavi.To_DoAPP.exception.UserNotFoundException;
import dev.eudavi.To_DoAPP.exception.WeakPasswordException;
import dev.eudavi.To_DoAPP.model.UserModel;
import dev.eudavi.To_DoAPP.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel save(UserDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        validarSenhaCadastro(dto.getPassword());

        UserModel userModel = new UserModel();
        userModel.setName(dto.getName());
        userModel.setEmail(dto.getEmail());
        userModel.setPassword(dto.getPassword());

        return userRepository.save(userModel);
    }

    public UserModel login(String email, String password) {
        UserModel userModel = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found."));

        if (!userModel.getPassword().equals(password)) {
            throw new InvalidPasswordException("Password invalid.");

        }
        return userModel;
    }

    public void validarSenhaCadastro(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";

        if (password == null || !password.matches(regex)) {
            throw new WeakPasswordException("Weak password: user at least 8 characters, with letters and numbers.");
        }
    }
}
