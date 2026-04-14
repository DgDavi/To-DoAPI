package dev.eudavi.To_DoAPP.dto;


import dev.eudavi.To_DoAPP.model.UserModel;

public class UserResponseDTO {
    Long Id;
    String Name;
    String Email;

    public UserResponseDTO (Long Id, String Name, String Email) {
        this.Id = Id;
        this.Name = Name;
        this.Email = Email;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public static UserResponseDTO toDTO(UserModel userModel) {
        return new UserResponseDTO(
                userModel.getId(),
                userModel.getName(),
                userModel.getEmail()
        );
    }
}
