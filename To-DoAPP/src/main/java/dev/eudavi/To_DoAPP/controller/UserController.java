package dev.eudavi.To_DoAPP.controller;

import dev.eudavi.To_DoAPP.dto.LoginDTO;
import dev.eudavi.To_DoAPP.dto.LoginResponseDTO;
import dev.eudavi.To_DoAPP.dto.UserDTO;
import dev.eudavi.To_DoAPP.dto.UserRequestDTO;
import dev.eudavi.To_DoAPP.dto.UserResponseDTO;
import dev.eudavi.To_DoAPP.model.UserModel;
import dev.eudavi.To_DoAPP.security.JwtUtil;
import dev.eudavi.To_DoAPP.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public  UserModel criarUsuario(@RequestBody @Valid UserDTO dto) {
        return userService.save(dto);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO dto) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = jwtUtil.generateToken(auth.getName());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @GetMapping("/read")
    public UserResponseDTO exibirUsuario(Authentication authentication) {
        String email = authentication.getName();
        UserModel user = userService.exibirUsuario(email);
        return UserResponseDTO.toDTO(user);
    }

    @PatchMapping("/edit")
    public UserResponseDTO editarUsuario(@RequestBody @Valid UserRequestDTO dto, Authentication authentication ) {
        String currentEmail = authentication.getName();
        UserModel user = userService.editarUsuario(currentEmail, dto);
        return UserResponseDTO.toDTO(user);
    }
}

