package ifc33b.dwesc.lista_juegos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ifc33b.dwesc.lista_juegos.dto.LoginRequest;
import ifc33b.dwesc.lista_juegos.dto.LoginResponse;
import ifc33b.dwesc.lista_juegos.dto.MessageResponse;
import ifc33b.dwesc.lista_juegos.dto.RegisterRequest;
import ifc33b.dwesc.lista_juegos.model.User;
import ifc33b.dwesc.lista_juegos.repository.UserRepository;
import ifc33b.dwesc.lista_juegos.security.JwtTokenUtil;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    // Loguear al usuario (Generar y enviar token)
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new LoginResponse(token, user.getUsername(), user.getEmail(), user.getRole());
    }

    // Registrar al usuario (Crear usuario y enviar mensaje)
    public MessageResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        User user = new User(request.getUsername(), passwordEncoder.encode((request.getPassword())), request.getEmail());
        userRepository.save(user);

        return new MessageResponse("Usuario registrado correctamente");
    }
}
