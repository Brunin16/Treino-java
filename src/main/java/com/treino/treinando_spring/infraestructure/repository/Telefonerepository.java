package com.treino.treinando_spring.infraestructure.repository;

import com.treino.treinando_spring.infraestructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Telefonerepository extends JpaRepository<Telefone, Long> {
}
