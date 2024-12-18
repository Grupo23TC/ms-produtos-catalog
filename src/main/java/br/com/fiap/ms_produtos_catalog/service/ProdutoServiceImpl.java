package br.com.fiap.ms_produtos_catalog.service;

import br.com.fiap.ms_produtos_catalog.dto.request.CadastrarProdutoRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoDeletadoResponseDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.exception.ProdutoNotFoundException;
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
                () -> new ProdutoNotFoundException("Produto com id: " + id + " não encontrado")
        );
        return ProdutoMapper.toProdutoResponse(produto);
    }

    @Override
    public ProdutoResponseDTO cadastrarProduto(CadastrarProdutoRequestDTO produtoRequest) {
        Produto produto = new Produto();
        produto.setNome(produtoRequest.nome());
        produto.setDescricao(produtoRequest.descricao());
        produto.setValor(produtoRequest.valor());
        produto.setQuantidadeEstoque(produtoRequest.quantidade());

        return ProdutoMapper.toProdutoResponse(repository.save(produto));
    }

    @Override
    @Transactional
    public ProdutoResponseDTO atualizarProduto(Long id, CadastrarProdutoRequestDTO request) {
        Produto produto = repository.findById(id).orElseThrow(
                () -> new ProdutoNotFoundException("Produto com id: " + id + " não encontrado")
        );
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setValor(request.valor());
        produto = repository.save(produto);

        return ProdutoMapper.toProdutoResponse(produto);
    }

    @Override
    public ProdutoDeletadoResponseDTO deletarProduto(Long id) {
            repository.findById(id).orElseThrow(
                    () -> new ProdutoNotFoundException("Produto com id: " + id + " não encontrado")
            );
            repository.deleteById(id);
            return new ProdutoDeletadoResponseDTO(true);
    }

    @Override
    public Produto atualizarQuantidadeProduto(Long id, int quantidade) {
        Produto produto = repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        if(quantidade > produto.getQuantidadeEstoque()) {
            throw new ProdutoNotFoundException("Quantidade requerida maior do que tem em estoque");
        } else {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        }

        return repository.save(produto);
    }


    @Override
    public Produto atualizarProdutoPedidoCancelado(Long id, int quantidade) {
        Produto produto = repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        if(quantidade <= 0) {
            throw new ProdutoNotFoundException("Quantidade não pode ser menor do que zero");
        }
        produto.setQuantidadeEstoque(quantidade + produto.getQuantidadeEstoque());

        return repository.save(produto);
    }
}
