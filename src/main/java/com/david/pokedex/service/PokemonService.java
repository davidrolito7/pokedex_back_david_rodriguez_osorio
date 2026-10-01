package com.david.pokedex.service;

import com.david.pokedex.dto.PokemonRequest;
import com.david.pokedex.model.Entrenador;
import com.david.pokedex.model.Pokemon;
import com.david.pokedex.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PokemonService {

    private static final int MAX_POKEMON_POR_ENTRENADOR = 3;

    private final PokemonRepository repo;
    private final EntrenadorService entrenadorService;
    private final TipoService tipoService;

    public PokemonService(PokemonRepository repo, EntrenadorService entrenadorService, TipoService tipoService) {
        this.repo = repo;
        this.entrenadorService = entrenadorService;
        this.tipoService = tipoService;
    }

    public List<Pokemon> listar(LocalDate fechaInicio, LocalDate fechaFin, Long tipoId) {
        if (fechaInicio != null && fechaFin != null && fechaInicio.isAfter(fechaFin)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "fechaInicio no puede ser posterior a fechaFin");
        }
        return repo.filtrar(fechaInicio, fechaFin, tipoId);
    }

    public Pokemon obtener(Long id) {
        return repo.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pokemon no encontrado"));
    }

    public Pokemon crear(PokemonRequest request) {
        Pokemon pokemon = new Pokemon();
        copiarDatos(request, pokemon);
        return repo.save(pokemon);
    }

    public Pokemon actualizar(Long id, PokemonRequest request) {
        Pokemon existente = obtener(id);
        copiarDatos(request, existente);
        return repo.save(existente);
    }

    public void eliminar(Long id) {
        Pokemon existente = obtener(id);
        existente.setActivo(false);
        repo.save(existente);
    }

    private void copiarDatos(PokemonRequest request, Pokemon pokemon) {
        pokemon.setNombre(request.nombre());
        pokemon.setTipo(tipoService.obtener(request.tipoId()));
        pokemon.setNivel(request.nivel());
        pokemon.setHp(request.hp());
        pokemon.setFechaCaptura(request.fechaCaptura());
        pokemon.setImagenUrl(request.imagenUrl());
        Entrenador entrenador = resolverEntrenador(request.entrenadorId());
        validarCupo(entrenador, pokemon);
        pokemon.setEntrenador(entrenador);
    }


    private Entrenador resolverEntrenador(Long entrenadorId) {
        return entrenadorId == null ? null : entrenadorService.obtener(entrenadorId);
    }

    // El entrenador no puede tener en este caso mas de 3 pokemones 0.o
    private void validarCupo(Entrenador nuevo, Pokemon pokemon) {
        if (nuevo == null) {
            return;
        }
        Entrenador actual = pokemon.getEntrenador();
        if (actual != null && actual.getId().equals(nuevo.getId())) {
            return;
        }
        if (repo.countByEntrenadorIdAndActivoTrue(nuevo.getId()) >= MAX_POKEMON_POR_ENTRENADOR) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "El entrenador ya tiene " + MAX_POKEMON_POR_ENTRENADOR + " Pokémon, su equipo esta completo 0.o");
        }
    }
}
