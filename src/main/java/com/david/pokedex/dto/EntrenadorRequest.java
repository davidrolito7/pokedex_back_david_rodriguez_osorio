package com.david.pokedex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EntrenadorRequest(
        @NotBlank @Size(max = 50) String nombre
) {
}
