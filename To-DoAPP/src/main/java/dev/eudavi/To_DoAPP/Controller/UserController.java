package dev.eudavi.To_DoAPP.Controller;

import dev.eudavi.To_DoAPP.Model.UserModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
class UserControllerController {

    @GetMapping("/{username}")
    public UserModel getUsuario(@PathVariable String username) {
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setEmail(username + "email");
        return user;
    }

    @PostMapping
    public  UserModel postUsuario(@RequestBody UserModel userModel) {
        return userModel;
    }
}

