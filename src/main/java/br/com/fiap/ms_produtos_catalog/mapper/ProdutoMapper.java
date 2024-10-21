package br.com.fiap.ms_produtos_catalog.mapper;

import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.model.Produto;

public class ProdutoMapper {

    public static ProdutoResponseDTO toProdutoResponse(Produto produto){
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getValor()
        );
    }
}
