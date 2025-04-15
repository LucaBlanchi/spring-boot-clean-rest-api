package it.lucablanchi.itemsspringapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRequestDto(
        @NotBlank String name,
        String description,
        @NotNull @Min(0) double price
) {}
