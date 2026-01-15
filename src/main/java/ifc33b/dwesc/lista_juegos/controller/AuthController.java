package ifc33b.dwesc.lista_juegos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import ifc33b.dwesc.lista_juegos.dto.LoginRequest;
import ifc33b.dwesc.lista_juegos.dto.LoginResponse;
import ifc33b.dwesc.lista_juegos.dto.MessageResponse;
import ifc33b.dwesc.lista_juegos.dto.RegisterRequest;
import ifc33b.dwesc.lista_juegos.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication", description = "Operaciones relacionadas con la gestión de los usuarios y tokens")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Respuesta de login enviada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "403", description = "Datos incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequest.class)))
    })
    @Operation(summary = "Loguearse", description = "Loguea al usuario en el servidor y devuelve un token JWT")
    @PostMapping("/login") // Loguea al usuario y le devuelve un token
    public ResponseEntity<LoginResponse> login(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario", required = true, content = @Content(schema = @Schema(implementation = LoginRequest.class))) @Valid @RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mensaje de respuesta enviado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Datos incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequest.class)))
    })
    @Operation(summary = "Registrarse", description = "Registra al usuario en el servidor y devuelve un mensaje informativo")
    @PostMapping("/register") // Registra al usuario y le devuelve un mensaje
    public ResponseEntity<MessageResponse> register(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del usuario", required = true, content = @Content(schema = @Schema(implementation = RegisterRequest.class))) @Valid @RequestBody RegisterRequest request) {
        try {
            MessageResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
