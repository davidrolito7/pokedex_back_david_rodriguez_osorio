package com.david.pokedex.service;

import com.david.pokedex.model.Tipo;
import com.david.pokedex.repository.TipoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TipoService {

    private final TipoRepository repo;

    public TipoService(TipoRepository repo) {
        this.repo = repo;
    }

    public List<Tipo> listar() {
        return repo.findAll(Sort.by("nombre"));
    }

    public Tipo obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tipo no encontrado"));
    }
}
