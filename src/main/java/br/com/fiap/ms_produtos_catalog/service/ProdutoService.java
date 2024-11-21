package br.com.fiap.ms_produtos_catalog.service;

import br.com.fiap.ms_produtos_catalog.dto.request.CadastrarProdutoRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoDeletadoResponseDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {

    Page<ProdutoResponseDTO> listarProdutos(Pageable pageable) ;

    ProdutoResponseDTO buscarProdutosPorId(Long id);

    ProdutoResponseDTO cadastrarProduto(CadastrarProdutoRequestDTO produtoRequest);

    ProdutoDeletadoResponseDTO deletarProduto(Long id);

    ProdutoResponseDTO atualizarProduto(Long id, CadastrarProdutoRequestDTO request);

    Produto atualizarQuantidadeProduto(Long produtoId, int quantidade);

    Produto atualizarProdutoPedidoCancelado(Long id, int quantidade);

}
