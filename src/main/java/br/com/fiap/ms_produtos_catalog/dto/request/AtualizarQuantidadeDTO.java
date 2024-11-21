package br.com.fiap.ms_produtos_catalog.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtualizarQuantidadeDTO(
    @NotNull(message = "O campo quantidade não pode ser nulo")
    @Positive(message = "A quantidade do produto precisa ser positiva")
    int quantidade
) {
}
