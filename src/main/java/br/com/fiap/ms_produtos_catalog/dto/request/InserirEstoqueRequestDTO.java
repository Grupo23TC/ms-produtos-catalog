package br.com.fiap.ms_produtos_catalog.dto.request;

import br.com.fiap.ms_produtos_catalog.model.Produto;

import java.time.LocalDate;

public record InserirEstoqueRequestDTO(
        Produto produto,
        int quantidade,
        LocalDate dataEntrada,
        LocalDate dataSaida
) {
}
