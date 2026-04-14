package dev.eudavi.To_DoAPP.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private String name;
    @Email(message = "Email inválido.")
    private String email;
}
