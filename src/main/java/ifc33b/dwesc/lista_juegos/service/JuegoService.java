package ifc33b.dwesc.lista_juegos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifc33b.dwesc.lista_juegos.dto.JuegoRequest;
import ifc33b.dwesc.lista_juegos.dto.JuegoResponse;
import ifc33b.dwesc.lista_juegos.exception.JuegoNotFoundException;
import ifc33b.dwesc.lista_juegos.model.Juego;
import ifc33b.dwesc.lista_juegos.repository.JuegoRepository;

@Service
public class JuegoService {
    @Autowired
    JuegoRepository juegoRepository;

    // Devuelve todos los juegos
    public List<JuegoResponse> getJuegos() {
        return this.juegoRepository.findAll().stream()
                .map(JuegoResponse::new)
                .collect(Collectors.toList());
    }

    // Devuelve todos los juegos comprados
    public List<JuegoResponse> getJuegosComprados() {
        return this.juegoRepository.findByComprado(true).stream()
                .map(JuegoResponse::new)
                .collect(Collectors.toList());
    }

    // Devuelve todos los juegos no comprados
    public List<JuegoResponse> getJuegosNoComprados() {
        return this.juegoRepository.findByComprado(false).stream()
                .map(JuegoResponse::new)
                .collect(Collectors.toList());
    }

    // Marcar juego como comprado
    public JuegoResponse updateComprado(Long id) {
        Juego juego = this.juegoRepository.findById(id)
            .orElseThrow(() -> new JuegoNotFoundException(id));

        juego.marcarComoComprado();

        juegoRepository.save(juego);

        return new JuegoResponse(juego);
    }

    // Añadir un nuevo juego
    public JuegoResponse createJuego(JuegoRequest request) {
        Juego juego = new Juego(request.getNombre(), request.getCategoria(), request.getPrecio(), request.getComprado());
        this.juegoRepository.save(juego);
        return new JuegoResponse(juego);
    }

    // Eliminar un juego
    public void deleteJuego(Long id) {
        if (!this.juegoRepository.existsById(id)) {
            throw new JuegoNotFoundException(id);
        }

        this.juegoRepository.deleteById(id);
    }
}
