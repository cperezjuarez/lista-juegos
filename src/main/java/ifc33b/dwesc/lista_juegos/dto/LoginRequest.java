package ifc33b.dwesc.lista_juegos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @Schema(description = "Nombre del usuario", example = "Sefirot")
    @NotBlank(message = "Introduce el nombre de usuario")
    private String username;
    
    @Schema(description = "Contraseña del usuario", example = "Sefirot123")
    @NotBlank(message = "Introduce la contraseña")
    private String password;
}
