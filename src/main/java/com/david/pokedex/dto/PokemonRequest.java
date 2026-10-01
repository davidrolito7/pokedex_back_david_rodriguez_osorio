package com.david.pokedex.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PokemonRequest(
        @NotBlank @Size(max = 50) String nombre,
        @NotNull Long tipoId,
        @NotNull @Min(1) @Max(100) Integer nivel,
        @NotNull @Min(1) @Max(100) Integer hp,
        @PastOrPresent LocalDate fechaCaptura,
        @Size(max = 255) String imagenUrl,
        Long entrenadorId
) {
}
