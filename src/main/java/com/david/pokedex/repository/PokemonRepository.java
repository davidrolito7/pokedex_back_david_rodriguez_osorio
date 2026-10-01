package com.david.pokedex.repository;

import com.david.pokedex.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findByActivoTrue();

    Optional<Pokemon> findByIdAndActivoTrue(Long id);
}
