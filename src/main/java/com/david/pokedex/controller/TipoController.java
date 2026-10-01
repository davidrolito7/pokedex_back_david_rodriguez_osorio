package com.david.pokedex.controller;

import com.david.pokedex.dto.GenericResponse;
import com.david.pokedex.model.Tipo;
import com.david.pokedex.service.TipoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TipoController {

    private final TipoService service;

    public TipoController(TipoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<Tipo>>> listar() {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Tipos obtenidos correctamente", service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Tipo>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(
                GenericResponse.ok(HttpStatus.OK, "Tipo encontrado", service.obtener(id)));
    }
}
