package com.david.pokedex.service;

import com.david.pokedex.model.Entrenador;
import com.david.pokedex.model.Pokemon;
import com.david.pokedex.model.Tipo;
import com.david.pokedex.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository repo;
    private final EntrenadorService entrenadorService;
    private final TipoService tipoService;

    public PokemonService(PokemonRepository repo, EntrenadorService entrenadorService, TipoService tipoService) {
        this.repo = repo;
        this.entrenadorService = entrenadorService;
        this.tipoService = tipoService;
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
        pokemon.setTipo(resolverTipo(pokemon.getTipo()));
        pokemon.setEntrenador(resolverEntrenador(pokemon.getEntrenador()));
        return repo.save(pokemon);
    }

    public Pokemon actualizar(Long id, Pokemon datos) {
        Pokemon existente = obtener(id);
        existente.setNombre(datos.getNombre());
        existente.setTipo(resolverTipo(datos.getTipo()));
        existente.setNivel(datos.getNivel());
        existente.setHp(datos.getHp());
        existente.setFechaCaptura(datos.getFechaCaptura());
        existente.setImagenUrl(datos.getImagenUrl());
        existente.setEntrenador(resolverEntrenador(datos.getEntrenador()));
        return repo.save(existente);
    }

    public void eliminar(Long id) {
        Pokemon existente = obtener(id);
        existente.setActivo(false);
        repo.save(existente);
    }

    // El tipo es obligatorio y debe existir en el catálogo (si no, 404)
    private Tipo resolverTipo(Tipo tipo) {
        if (tipo.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id del tipo es obligatorio");
        }
        return tipoService.obtener(tipo.getId());
    }

    // El entrenador es opcional; si viene, debe existir y estar activo (si no, 404)
    private Entrenador resolverEntrenador(Entrenador entrenador) {
        if (entrenador == null || entrenador.getId() == null) {
            return null;
        }
        return entrenadorService.obtener(entrenador.getId());
    }
}
