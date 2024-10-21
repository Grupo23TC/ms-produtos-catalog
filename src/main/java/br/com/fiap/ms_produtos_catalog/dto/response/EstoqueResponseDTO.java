package br.com.fiap.ms_produtos_catalog.dto.response;

import br.com.fiap.ms_produtos_catalog.model.Produto;

import java.time.LocalDate;

public record EstoqueResponseDTO(
        Long id,
        Produto produto,
        LocalDate dataEntrada,
        LocalDate dataSaida,
        int quantidade
){
}

