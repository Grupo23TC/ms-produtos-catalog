package br.com.fiap.ms_produtos_catalog.service;

import br.com.fiap.ms_produtos_catalog.dto.request.InserirEstoqueRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.EstoqueResponseDTO;
import br.com.fiap.ms_produtos_catalog.model.Estoque;
import br.com.fiap.ms_produtos_catalog.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstoqueService {

    public Produto decrementarEstoque(Long produtoId, int quantidade);

    public Produto incrementarEstoque(Long produtoId, int quantidade);

    public Page<EstoqueResponseDTO> listarEstoque(Pageable pageable);

    public EstoqueResponseDTO buscarEstoquePorId(Long id);

    EstoqueResponseDTO inserirProdutoNoEstoque(InserirEstoqueRequestDTO estoque);
}
