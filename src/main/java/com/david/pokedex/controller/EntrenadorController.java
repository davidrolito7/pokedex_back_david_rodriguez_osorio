package com.david.pokedex.controller;

import com.david.pokedex.dto.GenericResponse;
import com.david.pokedex.model.Entrenador;
import com.david.pokedex.service.EntrenadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    private final EntrenadorService service;

    public EntrenadorController(EntrenadorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<Entrenador>>> listar() {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Entrenadores obtenidos correctamente", service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Entrenador>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Entrenador encontrado", service.obtener(id)));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<Entrenador>> crear(@Valid @RequestBody Entrenador entrenador) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericResponse.ok(HttpStatus.CREATED, "Entrenador creado correctamente", service.crear(entrenador)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Entrenador>> actualizar(@PathVariable Long id,
                                                                  @Valid @RequestBody Entrenador entrenador) {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Entrenador actualizado correctamente", service.actualizar(id, entrenador)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(GenericResponse.ok(HttpStatus.OK, "Entrenador desactivado correctamente", null));
    }
}
