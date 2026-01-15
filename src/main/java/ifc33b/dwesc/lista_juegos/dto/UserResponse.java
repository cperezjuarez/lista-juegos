package ifc33b.dwesc.lista_juegos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    @Schema(description = "ID del usuario", example = "1")
    private Long id;
    @Schema(description = "Nombre del usuario", example = "Sefirot")
    private String username;
    @Schema(description = "Email del ususario", example = "sefirot@gmail.com")
    private String email;
    @Schema(description = "Rol del usuario", example = "USER")
    private String role;
    @Schema(description = "Estado de la cuenta del usuario", example = "True")
    private boolean enabled;
}
