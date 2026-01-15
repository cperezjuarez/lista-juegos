package ifc33b.dwesc.lista_juegos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @Schema(description = "Nombre del usuario", example = "Sefirot")
    @NotBlank(message = "Se requiere nombre de usuario")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe de ser entre 3 y 50 caracteres")
    private String username;
    
    @Schema(description = "Contraseña del usuario", example = "Sefirot123")
    @NotBlank(message = "Se requiere contraseña")
    @Size(min = 6, message = "La contraseña debe de ser mínimo de 6 caracteres")
    private String password;
    
    @Schema(description = "Email del ususario", example = "sefirot@gmail.com")
    @NotBlank(message = "Se requiere email")
    @Email(message = "El mail debe ser válido")
    private String email;
    
    @Schema(description = "Rol del usuario", example = "USER")
    @NotBlank(message = "Se requiere el rol")
    private String role;
    
    @Schema(description = "Estado de la cuenta del usuario", example = "True")
    @NotEmpty(message = "Se requiere información del estado de la cuenta")
    private Boolean enabled;
}
