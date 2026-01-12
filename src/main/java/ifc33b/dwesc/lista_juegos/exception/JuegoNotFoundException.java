package ifc33b.dwesc.lista_juegos.exception;

public class JuegoNotFoundException extends RuntimeException {
    public JuegoNotFoundException(Long id) {
        super("No se ha encontrado el juego con la ID: " + id);
    }
}
