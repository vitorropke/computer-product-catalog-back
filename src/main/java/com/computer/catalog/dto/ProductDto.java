package com.computer.catalog.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductDto(@NotBlank String brand, @NotBlank String model, @NotBlank String batch, int quantity,
                         BigDecimal price, BigDecimal discount, String imageUrl) {
}
