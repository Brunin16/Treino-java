package com.treino.treinando_spring.controller;

import com.treino.treinando_spring.business.UsuarioService;
import com.treino.treinando_spring.controller.dto.UsuarioDTO;
import com.treino.treinando_spring.infraestructure.entity.Usuario;
import com.treino.treinando_spring.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService us;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario u){
        return ResponseEntity.ok(us.save(u));
    }
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.getEmail(),
                        usuarioDTO.getSenha()
                )
        );
        return "Bearer :" + jwtUtil.generateToken(authentication.getName());
    }
    @GetMapping
    public ResponseEntity<Usuario> getByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(us.getByEmail(email));
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> remove(@PathVariable String email){
        us.deleteByEmail(email);
        return ResponseEntity.notFound().build();
    }
}
