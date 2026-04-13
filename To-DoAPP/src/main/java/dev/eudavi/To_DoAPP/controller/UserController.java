package dev.eudavi.To_DoAPP.controller;

import dev.eudavi.To_DoAPP.dto.LoginDTO;
import dev.eudavi.To_DoAPP.dto.UserDTO;
import dev.eudavi.To_DoAPP.model.UserModel;
import dev.eudavi.To_DoAPP.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public  UserModel criarUsuario(@RequestBody @Valid UserDTO dto) {
        return userService.save(dto);
    }

    @PostMapping("/login")
    public UserModel login(@RequestBody @Valid LoginDTO dto) {
        return userService.login(dto.getEmail(), dto.getPassword());
    }

}

