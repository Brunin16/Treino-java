package com.treino.treinando_spring.infraestructure.repository;

import com.treino.treinando_spring.infraestructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
    @Transactional
    Void deleteByEmail(String email);
}
