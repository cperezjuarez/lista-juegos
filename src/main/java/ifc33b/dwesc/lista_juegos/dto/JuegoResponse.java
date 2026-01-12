package ifc33b.dwesc.lista_juegos.dto;

import ifc33b.dwesc.lista_juegos.model.Juego;
import lombok.Data;

@Data
public class JuegoResponse {
    private Long id;
    private String nombre;
    private String categoria;
    private Integer precio;
    private Boolean comprado;

    public JuegoResponse(Juego juego) {
        this.setId(juego.getId());
        this.setNombre(juego.getNombre());
        this.setCategoria(juego.getCategoria());
        this.setPrecio(juego.getPrecio());
        this.setComprado(juego.getComprado());
    }
}
