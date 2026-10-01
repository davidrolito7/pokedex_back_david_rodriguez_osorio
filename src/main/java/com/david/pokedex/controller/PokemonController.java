package com.david.pokedex.controller;

import com.david.pokedex.dto.GenericResponse;
import com.david.pokedex.dto.PokemonRequest;
import com.david.pokedex.model.Pokemon;
import com.david.pokedex.service.PokemonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Pokémon", description = "Gestión de Pokémon con filtros por fecha de captura y tipo")
@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<Pokemon>>> listar(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false) Long tipoId) {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Pokemon obtenidos correctamente", service.listar(fechaInicio, fechaFin, tipoId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Pokemon>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Pokemon encontrado", service.obtener(id)));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<Pokemon>> crear(@Valid @RequestBody PokemonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericResponse.ok(HttpStatus.CREATED, "Pokemon creado correctamente", service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Pokemon>> actualizar(@PathVariable Long id,
                                                               @Valid @RequestBody PokemonRequest request) {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Pokemon actualizado correctamente", service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(GenericResponse.ok(HttpStatus.OK, "Pokemon desactivado correctamente", null));
    }
}
