package ifc33b.dwesc.lista_juegos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JuegoRequest {
    @NotBlank(message = "Se necesita un nombre para juego")
    private String nombre;

    private String categoria;

    @NotNull(message = "Se necesita un precio para el juego")
    @Min(0)
    private Integer precio;

    @NotNull(message = "Se necesita saber si está comprado o no")
    private Boolean comprado;

    public JuegoRequest(String nombre, String categoria, Integer precio, Boolean comprado) {
        this.setNombre(nombre);
        this.setCategoria(categoria);
        this.setPrecio(precio);
        this.setComprado(comprado);
    }
}
