package ifc33b.dwesc.lista_juegos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ifc33b.dwesc.lista_juegos.dto.JuegoRequest;
import ifc33b.dwesc.lista_juegos.dto.JuegoResponse;
import ifc33b.dwesc.lista_juegos.exception.JuegoNotFoundException;
import ifc33b.dwesc.lista_juegos.service.JuegoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Juegos", description = "Operaciones relacionadas con la gestión de los juegos")
@RestController
@RequestMapping("juegos")
public class JuegoController {
    @Autowired
    JuegoService juegoService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de juegos devuelta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JuegoResponse.class))),
    })
    @Operation(summary = "Obtener juegos", description = "Devuelve una lista de todos los juegos disponibles en la base de datos")
    @GetMapping() // Devuelve todos los juegos
    public ResponseEntity<List<JuegoResponse>> getJuegos() {
        // Service
        List<JuegoResponse> response = this.juegoService.getJuegos();

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de juegos devuelta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JuegoResponse.class))),
    })
    @Operation(summary = "Obtener juegos comprados", description = "Devuelve una lista de todos los juegos marcados como comprados")
    @GetMapping("/comprados") // Devuelve todos los juegos comprados
    public ResponseEntity<List<JuegoResponse>> getJuegosComprados() {
        // Service
        List<JuegoResponse> response = this.juegoService.getJuegosComprados();

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de juegos devuelta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JuegoResponse.class))),
    })
    @Operation(summary = "Obtener juegos no comprados", description = "Devuelve una lista de todos los juegos sin marcar como comprados")
    @GetMapping("/noComprados") // Devuelve todos los juegos no comprados
    public ResponseEntity<List<JuegoResponse>> getJuegosNoComprados() {
        // Service
        List<JuegoResponse> response = this.juegoService.getJuegosNoComprados();

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Juego actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JuegoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Juego no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JuegoNotFoundException.class)))
    })
    @Operation(summary = "Marcar juego como comprado", description = "Marca el juego como comprado y devuelve el juego actualizado")
    @PutMapping("/{id}")
    public ResponseEntity<JuegoResponse> updateComprado(
            @Parameter(description = "ID del juego", example = "1", required = true) @PathVariable Long id) {
        // Service
        JuegoResponse response = this.juegoService.updateComprado(id);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Juego creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JuegoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequest.class))),
    })
    @Operation(summary = "Añadir juego", description = "Crea el juego en la base de datos y devuelve el juego nuevo")
    @PostMapping()
    public ResponseEntity<JuegoResponse> createJuego(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del juego a crear", required = true, content = @Content(schema = @Schema(implementation = JuegoRequest.class))) @Valid @RequestBody JuegoRequest request) {
        // Service
        JuegoResponse response = this.juegoService.createJuego(request);

        // HTTP Response
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Juego eliminado"),
            @ApiResponse(responseCode = "404", description = "Juego no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JuegoNotFoundException.class)))
    })
    @Operation(summary = "Eliminar juego", description = "Elimina el juego de la base de datos")
    @DeleteMapping("/{id}")
    public ResponseEntity<JuegoResponse> deleteJuego(
            @Parameter(description = "ID del juego", example = "1", required = true) @PathVariable Long id) {
        // Service
        this.juegoService.deleteJuego(id);

        // HTTP Response
        return ResponseEntity.noContent().build();
    }
}
