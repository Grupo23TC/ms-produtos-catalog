package br.com.fiap.ms_produtos_catalog.dto.request;

import java.math.BigDecimal;

public record CadastrarProdutoRequestDTO(
        String nome,
        String descricao,
        BigDecimal valor

){
}
