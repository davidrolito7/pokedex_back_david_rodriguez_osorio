package com.david.pokedex.service;

import com.david.pokedex.model.Entrenador;
import com.david.pokedex.model.Pokemon;
import com.david.pokedex.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository repo;
    private final EntrenadorService entrenadorService;

    public PokemonService(PokemonRepository repo, EntrenadorService entrenadorService) {
        this.repo = repo;
        this.entrenadorService = entrenadorService;
    }

    public List<Pokemon> listar() {
        return repo.findByActivoTrue();
    }

    public Pokemon obtener(Long id) {
        return repo.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pokemon no encontrado"));
    }

    public Pokemon crear(Pokemon pokemon) {
        pokemon.setId(null);
        pokemon.setActivo(true);
        pokemon.setEntrenador(resolverEntrenador(pokemon.getEntrenador()));
        return repo.save(pokemon);
    }

    public Pokemon actualizar(Long id, Pokemon datos) {
        Pokemon existente = obtener(id);
        existente.setNombre(datos.getNombre());
        existente.setTipo(datos.getTipo());
        existente.setNivel(datos.getNivel());
        existente.setHp(datos.getHp());
        existente.setFechaCaptura(datos.getFechaCaptura());
        existente.setEntrenador(resolverEntrenador(datos.getEntrenador()));
        return repo.save(existente);
    }

    public void eliminar(Long id) {
        Pokemon existente = obtener(id);
        existente.setActivo(false);
        repo.save(existente);
    }

    // El entrenador es opcional; si viene, debe existir y estar activo (si no, 404)
    private Entrenador resolverEntrenador(Entrenador entrenador) {
        if (entrenador == null || entrenador.getId() == null) {
            return null;
        }
        return entrenadorService.obtener(entrenador.getId());
    }
}
