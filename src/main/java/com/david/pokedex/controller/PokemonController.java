package com.david.pokedex.controller;

import com.david.pokedex.dto.GenericResponse;
import com.david.pokedex.model.Pokemon;
import com.david.pokedex.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<Pokemon>>> listar() {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Pokemon obtenidos correctamente", service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Pokemon>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Pokemon encontrado", service.obtener(id)));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<Pokemon>> crear(@Valid @RequestBody Pokemon pokemon) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericResponse.ok(HttpStatus.CREATED, "Pokemon creado correctamente", service.crear(pokemon)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Pokemon>> actualizar(@PathVariable Long id,
                                                               @Valid @RequestBody Pokemon pokemon) {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Pokemon actualizado correctamente", service.actualizar(id, pokemon)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(GenericResponse.ok(HttpStatus.OK, "Pokemon desactivado correctamente", null));
    }
}
