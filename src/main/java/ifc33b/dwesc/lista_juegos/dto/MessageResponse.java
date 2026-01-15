package ifc33b.dwesc.lista_juegos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {
    @Schema(description = "Mensaje de respuesta", example = "Usuario registrado correctamente")
    private String message;
}
