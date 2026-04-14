package dev.eudavi.To_DoAPP.service;

import dev.eudavi.To_DoAPP.dto.UserDTO;
import dev.eudavi.To_DoAPP.dto.UserRequestDTO;
import dev.eudavi.To_DoAPP.exception.EmailAlreadyExistsException;
import dev.eudavi.To_DoAPP.exception.UserNotFoundException;
import dev.eudavi.To_DoAPP.exception.WeakPasswordException;
import dev.eudavi.To_DoAPP.model.UserModel;
import dev.eudavi.To_DoAPP.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel save(UserDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        validarSenhaCadastro(dto.getPassword());

        UserModel userModel = new UserModel();
        userModel.setName(dto.getName());
        userModel.setEmail(dto.getEmail());
        userModel.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(userModel);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public void validarSenhaCadastro(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";

        if (password == null || !password.matches(regex)) {
            throw new WeakPasswordException("Weak password: user at least 8 characters, with letters and numbers.");
        }

    }

    public UserModel exibirUsuario(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

    }

    public UserModel editarUsuario(String currentEmail, UserRequestDTO dto) {
        UserModel user = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + currentEmail));

        boolean hasName = dto.getName() != null && !dto.getName().isBlank();
        boolean hasEmail = dto.getEmail() != null && !dto.getEmail().isBlank();

        if (!hasName && !hasEmail) {
            throw new IllegalArgumentException("At least one field (name or email) is required to update user.");
        }

        if (hasName) {
            user.setName(dto.getName().trim());
        }

        if (hasEmail) {
            String newEmail = dto.getEmail().trim();
            boolean isEmailChanging = !newEmail.equalsIgnoreCase(user.getEmail());

            if (isEmailChanging && userRepository.findByEmail(newEmail).isPresent()) {
                throw new EmailAlreadyExistsException("Email already exists.");
            }

            user.setEmail(newEmail);
        }

        return userRepository.save(user);
    }

}
