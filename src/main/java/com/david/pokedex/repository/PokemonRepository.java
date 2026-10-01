package com.david.pokedex.repository;

import com.david.pokedex.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    Optional<Pokemon> findByIdAndActivoTrue(Long id);

    long countByEntrenadorIdAndActivoTrue(Long entrenadorId);

    @Query("""
            SELECT p FROM Pokemon p
            WHERE p.activo = true
              AND (CAST(:fechaInicio AS LocalDate) IS NULL OR p.fechaCaptura >= :fechaInicio)
              AND (CAST(:fechaFin AS LocalDate) IS NULL OR p.fechaCaptura <= :fechaFin)
              AND (CAST(:tipoId AS Long) IS NULL OR p.tipo.id = :tipoId)
            """)
    List<Pokemon> filtrar(LocalDate fechaInicio, LocalDate fechaFin, Long tipoId);
}
