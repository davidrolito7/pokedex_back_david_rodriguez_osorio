package com.david.pokedex.repository;

import com.david.pokedex.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {

    List<Entrenador> findByActivoTrue();

    Optional<Entrenador> findByIdAndActivoTrue(Long id);
}
