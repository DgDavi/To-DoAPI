package dev.eudavi.To_DoAPP.Controller;

import dev.eudavi.To_DoAPP.DTO.LoginDTO;
import dev.eudavi.To_DoAPP.DTO.UserDTO;
import dev.eudavi.To_DoAPP.Model.UserModel;
import dev.eudavi.To_DoAPP.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public  UserModel criarUsuario(@RequestBody @Valid UserDTO dto) {
        return userService.salvar(dto);
    }

    @PostMapping("/login")
    public UserModel login(@RequestBody @Valid LoginDTO dto) {
        return userService.login(dto.getEmail(), dto.getPassword());
    }

}

