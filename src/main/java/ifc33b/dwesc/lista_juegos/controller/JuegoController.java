package ifc33b.dwesc.lista_juegos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifc33b.dwesc.lista_juegos.dto.JuegoRequest;
import ifc33b.dwesc.lista_juegos.dto.JuegoResponse;
import ifc33b.dwesc.lista_juegos.service.JuegoService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("juegos")
public class JuegoController {
    @Autowired
    JuegoService juegoService;

    @GetMapping() // Devuelve todos los juegos
    public ResponseEntity<List<JuegoResponse>> getJuegos() {
        // Service
        List<JuegoResponse> response = this.juegoService.getJuegos();

        // HTTP Response
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/comprados") // Devuelve todos los juegos comprados
    public ResponseEntity<List<JuegoResponse>> getJuegosComprados() {
        // Service
        List<JuegoResponse> response = this.juegoService.getJuegosComprados();

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @GetMapping("/noComprados") // Devuelve todos los juegos no comprados
    public ResponseEntity<List<JuegoResponse>>  getJuegosNoComprados() {
        // Service
        List<JuegoResponse> response = this.juegoService.getJuegosNoComprados();

        // HTTP Response
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<JuegoResponse> updateComprado(@PathVariable Long id) {
        // Service
        JuegoResponse response = this.juegoService.updateComprado(id);

        // HTTP Response
        return ResponseEntity.ok(response);
    }
    
    @PostMapping()
    public ResponseEntity<JuegoResponse> createJuego(@Valid @RequestBody JuegoRequest request) {
        // Service
        JuegoResponse response = this.juegoService.createJuego(request);

        // HTTP Response
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JuegoResponse> deleteJuego(@PathVariable Long id) {
        // Service
        this.juegoService.deleteJuego(id);

        // HTTP Response
        return ResponseEntity.noContent().build();
    }
}
