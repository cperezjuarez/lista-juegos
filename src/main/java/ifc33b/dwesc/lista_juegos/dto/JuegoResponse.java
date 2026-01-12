package ifc33b.dwesc.lista_juegos.dto;

import ifc33b.dwesc.lista_juegos.model.Juego;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "DTO de Respuesta")
@Data
public class JuegoResponse {
    @Schema(description = "Identificador único", example = "7")
    private Long id;
    @Schema(description = "Nombre del juego", example = "Final Fantasy VII")
    private String nombre;
    @Schema(description = "Genero del juego", example = "JRPG")
    private String categoria;
    @Schema(description = "Precio del juego", example = "60")
    private Integer precio;
    @Schema(description = "Campo para saber si lo tienes comprado o no", example = "true")
    private Boolean comprado;

    public JuegoResponse(Juego juego) {
        this.setId(juego.getId());
        this.setNombre(juego.getNombre());
        this.setCategoria(juego.getCategoria());
        this.setPrecio(juego.getPrecio());
        this.setComprado(juego.getComprado());
    }
}
