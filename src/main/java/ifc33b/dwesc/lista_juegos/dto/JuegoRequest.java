package ifc33b.dwesc.lista_juegos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "DTO de Request")
@Data
public class JuegoRequest {
    @Schema(description = "Nombre del juego", example = "Final Fantasy VII")
    @NotBlank(message = "Se necesita un nombre para juego")
    private String nombre;

    @Schema(description = "Genero del juego", example = "JRPG")
    private String categoria;

    @Schema(description = "Precio del juego", example = "60")
    @NotNull(message = "Se necesita un precio para el juego")
    @Min(0)
    private Integer precio;

    @Schema(description = "Campo para saber si lo tienes comprado o no", example = "true")
    @NotNull(message = "Se necesita saber si está comprado o no")
    private Boolean comprado;

    public JuegoRequest(String nombre, String categoria, Integer precio, Boolean comprado) {
        this.setNombre(nombre);
        this.setCategoria(categoria);
        this.setPrecio(precio);
        this.setComprado(comprado);
    }
}
