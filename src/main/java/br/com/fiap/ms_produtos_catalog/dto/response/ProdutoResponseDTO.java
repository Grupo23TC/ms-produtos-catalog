package br.com.fiap.ms_produtos_catalog.dto.response;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor
) {
}
