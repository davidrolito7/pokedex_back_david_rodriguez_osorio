package com.david.pokedex.service;

import com.david.pokedex.dto.EntrenadorRequest;
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

    public Entrenador crear(EntrenadorRequest request) {
        Entrenador entrenador = new Entrenador();
        entrenador.setNombre(request.nombre());
        return repo.save(entrenador);
    }

    public Entrenador actualizar(Long id, EntrenadorRequest request) {
        Entrenador existente = obtener(id);
        existente.setNombre(request.nombre());
        return repo.save(existente);
    }

    public void eliminar(Long id) {
        Entrenador existente = obtener(id);
        existente.setActivo(false);
        repo.save(existente);
    }
}
