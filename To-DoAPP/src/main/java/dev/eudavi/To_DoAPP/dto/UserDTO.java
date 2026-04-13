package dev.eudavi.To_DoAPP.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDTO {
    @NotBlank(message = "Nome obrigatório.")
    private String name;
    @NotBlank(message = "Email obrigatório.")
    @Email(message = "Email inválido.")
    private String email;
    @NotBlank(message = "Senha obrigatória.")

    private String password;

    public UserDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
