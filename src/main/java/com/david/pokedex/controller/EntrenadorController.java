package com.david.pokedex.controller;

import com.david.pokedex.dto.EntrenadorRequest;
import com.david.pokedex.dto.GenericResponse;
import com.david.pokedex.model.Entrenador;
import com.david.pokedex.service.EntrenadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Entrenadores", description = "Gestión de entrenadores")
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
    public ResponseEntity<GenericResponse<Entrenador>> crear(@Valid @RequestBody EntrenadorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericResponse.ok(HttpStatus.CREATED, "Entrenador creado correctamente", service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Entrenador>> actualizar(@PathVariable Long id,
                                                                  @Valid @RequestBody EntrenadorRequest request) {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Entrenador actualizado correctamente", service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(GenericResponse.ok(HttpStatus.OK, "Entrenador desactivado correctamente", null));
    }
}
