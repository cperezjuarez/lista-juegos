package ifc33b.dwesc.lista_juegos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "juegos")
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Se necesita un nombre para juego")
    private String nombre;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    @NotNull(message = "Se necesita un precio para el juego")
    @Min(0)
    private Integer precio;

    @Column(nullable = false)
    @NotNull(message = "Se necesita saber si está comprado o no")
    private Boolean comprado;

    public Juego() {}

    public Juego(String nombre, String categoria, Integer precio, Boolean comprado) {
        this.setNombre(nombre);
        this.setCategoria(categoria);
        this.setPrecio(precio);
        this.setComprado(comprado);
    }

    public void marcarComoComprado() {
        this.setComprado(true);
    }
}
