package ifc33b.dwesc.lista_juegos.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Excepción de Juego no encontardo")
public class JuegoNotFoundException extends RuntimeException {
    public JuegoNotFoundException(Long id) {
        super("No se ha encontrado el juego con la ID: " + id);
    }
}
