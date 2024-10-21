package br.com.fiap.ms_produtos_catalog.mapper;

import br.com.fiap.ms_produtos_catalog.dto.response.EstoqueResponseDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.model.Estoque;
import br.com.fiap.ms_produtos_catalog.model.Produto;

public class EstoqueMapper {

    public static EstoqueResponseDTO toEstoqueResponse(Estoque estoque){
        return new EstoqueResponseDTO(
                estoque.getId(),
                estoque.getProduto(),
                estoque.getDataEntrada(),
                estoque.getDataSaida(),
                estoque.getQuantidade()
        );
    }
}
