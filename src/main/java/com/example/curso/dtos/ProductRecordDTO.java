package com.example.curso.dtos;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductRecordDTO(@NotBlank String name, @NotNull BigDecimal price) {
}
