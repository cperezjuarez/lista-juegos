package ifc33b.dwesc.lista_juegos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Introduce el nombre de usuario")
    private String username;
    
    @NotBlank(message = "Introduce la contraseña")
    private String password;
}
