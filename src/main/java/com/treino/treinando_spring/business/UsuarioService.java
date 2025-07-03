package com.treino.treinando_spring.business;

import com.treino.treinando_spring.infraestructure.entity.Usuario;
import com.treino.treinando_spring.infraestructure.exceptions.ConflictException;
import com.treino.treinando_spring.infraestructure.exceptions.ResouceNotFindException;
import com.treino.treinando_spring.infraestructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository ur;
    private final PasswordEncoder passwordEncoder;

    public Usuario save(Usuario u){
        try {
            emailExist(u.getEmail());
            u.setSenha(passwordEncoder.encode(u.getPassword()));
            return ur.save(u);
        }catch (ConflictException e){
            throw new ConflictException("Email ja cadastrado" + e.getCause());
        }
    }
    public Usuario getByEmail(String email){
        return ur.findByEmail(email).orElseThrow(() -> new ResouceNotFindException("Email nao encontrado"));
    }
    public Void deleteByEmail(String email){
        return ur.deleteByEmail(email);
    }

    public void emailExist(String email){
        try {
            boolean exist = verifyExistByEmail(email);
            if(exist){
                throw new ConflictException("Email ja cadastrado" + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email ja cadastrado" + e.getCause());
        }
    }

    public boolean verifyExistByEmail(String email){
        return ur.existsByEmail(email);
    }
}
