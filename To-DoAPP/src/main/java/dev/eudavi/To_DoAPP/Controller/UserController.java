package dev.eudavi.To_DoAPP.Controller;

import dev.eudavi.To_DoAPP.DTO.UserDTO;
import dev.eudavi.To_DoAPP.Model.UserModel;
import dev.eudavi.To_DoAPP.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro")
class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public  UserModel criarUsuario(@RequestBody UserDTO dto) {
        return userService.salvar(dto);
    }

}

