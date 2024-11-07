package br.com.fiap.ms_produtos_catalog.dto.request;

import br.com.fiap.ms_produtos_catalog.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record InserirEstoqueRequestDTO(
        @NotBlank
        Produto produto,
        @Positive(message = "A quantidade do produto deve ser positiva")
        int quantidade,
        LocalDate dataEntrada,
        LocalDate dataSaida
) {
}
