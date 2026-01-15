package ifc33b.dwesc.lista_juegos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    @Schema(description = "Token de JWT", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZWZpcm90IiwiaWF0IjoxNzY4NTE1MDY4LCJleHAiOjE3Njg2MDE0Njh9.Uwa6vDEsGsl_j0YeKblnmdN25FWAWzNQyEbZSVIRouo")
    private String token;
    @Schema(description = "Tipo de token", example = "Bearer")
    private String type = "Bearer";
    @Schema(description = "Nombre del usuario", example = "Sefirot")
    private String username;
    @Schema(description = "Email del ususario", example = "sefirot@gmail.com")
    private String email;
    @Schema(description = "Rol del usuario", example = "USER")
    private String role;
    
    public LoginResponse(String token, String username, String email, String role) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
