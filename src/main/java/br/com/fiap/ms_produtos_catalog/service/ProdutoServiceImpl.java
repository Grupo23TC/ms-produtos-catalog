package br.com.fiap.ms_produtos_catalog.service;

import br.com.fiap.ms_produtos_catalog.dto.request.CadastrarProdutoRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoDeletadoResponseDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.mapper.ProdutoMapper;
import br.com.fiap.ms_produtos_catalog.model.Produto;
import br.com.fiap.ms_produtos_catalog.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    @Autowired
    public ProdutoRepository repository;

    @Override
    public Page<ProdutoResponseDTO> listarProdutos(Pageable pageable) {
        return repository.findAll(pageable).map(produto -> ProdutoMapper.toProdutoResponse(produto));
    }

    @Override
    public ProdutoResponseDTO buscarProdutosPorId(Long id) {
        Produto produto = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Produto com id: " + id + " não encontrado")
        );
        return ProdutoMapper.toProdutoResponse(produto);
    }

    @Override
    public ProdutoResponseDTO cadastrarProduto(CadastrarProdutoRequestDTO produtoRequest) {
        Produto produto = new Produto();
        produto.setNome(produtoRequest.nome());
        produto.setDescricao(produtoRequest.descricao());
        produto.setValor(produtoRequest.valor());

        return ProdutoMapper.toProdutoResponse(repository.save(produto));
    }

    @Override
    @Transactional
    public ProdutoResponseDTO atualizarProduto(Long id, CadastrarProdutoRequestDTO request) {
        Produto produto = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Produto com id: " + id + " não encontrado")
        );
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setValor(request.valor());
        produto = repository.save(produto);//TODO preciso salvar dnv?

        return ProdutoMapper.toProdutoResponse(produto);
    }

    @Override
    public ProdutoDeletadoResponseDTO deletarProduto(Long id) {
        try {
            repository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("Produto com id: " + id + " não encontrado")
            );
            repository.deleteById(id);
            return new ProdutoDeletadoResponseDTO(true);
        } catch (Exception e) {
            return new ProdutoDeletadoResponseDTO(false);
        }
    }
}
