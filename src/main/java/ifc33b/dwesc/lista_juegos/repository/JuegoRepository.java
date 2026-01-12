package ifc33b.dwesc.lista_juegos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifc33b.dwesc.lista_juegos.model.Juego;
import java.util.List;


@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {
    List<Juego> findByComprado(Boolean comprado);
}
