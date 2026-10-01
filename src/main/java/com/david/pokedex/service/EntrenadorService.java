package com.david.pokedex.service;

import com.david.pokedex.model.Entrenador;
import com.david.pokedex.repository.EntrenadorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository repo;

    public EntrenadorService(EntrenadorRepository repo) {
        this.repo = repo;
    }

    public List<Entrenador> listar() {
        return repo.findByActivoTrue();
    }

    public Entrenador obtener(Long id) {
        return repo.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entrenador no encontrado"));
    }

    public Entrenador crear(Entrenador entrenador) {
        entrenador.setId(null);
        entrenador.setActivo(true);
        return repo.save(entrenador);
    }

    public Entrenador actualizar(Long id, Entrenador datos) {
        Entrenador existente = obtener(id);
        existente.setNombre(datos.getNombre());
        return repo.save(existente);
    }

    public void eliminar(Long id) {
        Entrenador existente = obtener(id);
        existente.setActivo(false);
        repo.save(existente);
    }
}