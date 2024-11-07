package br.com.fiap.ms_produtos_catalog.service;

import br.com.fiap.ms_produtos_catalog.dto.request.InserirEstoqueRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.EstoqueResponseDTO;
import br.com.fiap.ms_produtos_catalog.exception.EstoqueNotFoundException;
import br.com.fiap.ms_produtos_catalog.exception.ProdutoNotFoundException;
import br.com.fiap.ms_produtos_catalog.mapper.EstoqueMapper;
import br.com.fiap.ms_produtos_catalog.mapper.ProdutoMapper;
import br.com.fiap.ms_produtos_catalog.model.Estoque;
import br.com.fiap.ms_produtos_catalog.model.Produto;
import br.com.fiap.ms_produtos_catalog.repository.EstoqueRepository;
import br.com.fiap.ms_produtos_catalog.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstoqueServiceImpl implements EstoqueService{

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Override
    public Page<EstoqueResponseDTO> listarEstoque(Pageable pageable) {
        return estoqueRepository.findAll(pageable).map(estoque -> EstoqueMapper.toEstoqueResponse(estoque));
    }

    @Override
    public EstoqueResponseDTO buscarEstoquePorId(Long id) {
        Estoque estoque = estoqueRepository.findById(id).orElseThrow(
                () -> new EstoqueNotFoundException("Estoque com id: " + id + " não encontrado")
        );
        return EstoqueMapper.toEstoqueResponse(estoque);
    }

    @Override
    public EstoqueResponseDTO inserirProdutoNoEstoque(InserirEstoqueRequestDTO estoqueRequestDTO) {

        Optional<Produto> produto = produtoRepository.findById(estoqueRequestDTO.produto().getId());

        if(produto.isEmpty()) {
            throw new ProdutoNotFoundException("Produto com id: " + produto.get().getId() + " não existe");
        }

        Estoque estoque = new Estoque();
        estoque.setProduto(estoqueRequestDTO.produto());
        estoque.setQuantidade(estoqueRequestDTO.quantidade());
        estoque.setDataEntrada(estoqueRequestDTO.dataEntrada());
        estoque.setDataSaida(estoqueRequestDTO.dataSaida());

        return EstoqueMapper.toEstoqueResponse(estoqueRepository.save(estoque));
    }

    @Override
    public Produto decrementarEstoque(Long produtoId, int quantidade) { //TODO Alterar a quantidade de produto na tabela Estoque

        Produto produto = produtoRepository.findById(produtoId).orElse(null);

        if (produto != null) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
            return produtoRepository.save(produto);
        }
        return null;
    }

    @Override
    public Produto incrementarEstoque(Long produtoId, int quantidade) { //TODO Alterar a quantidade de produto na tabela Estoque
        Produto produto = produtoRepository.findById(produtoId).orElse(null);

        if (produto != null) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
            return produtoRepository.save(produto);
        }
        return null;
    }
}
