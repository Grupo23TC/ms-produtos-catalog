package br.com.fiap.ms_produtos_catalog.controller;

import br.com.fiap.ms_produtos_catalog.dto.request.AtualizarQuantidadeDTO;
import br.com.fiap.ms_produtos_catalog.dto.request.CadastrarProdutoRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoDeletadoResponseDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.model.Produto;
import br.com.fiap.ms_produtos_catalog.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    public ProdutoController(ProdutoService produtoService) {
        this.service = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDTO>> listarProdutos(
            @PageableDefault(page = 0, size = 20) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarProdutos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarProdutosPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@RequestBody CadastrarProdutoRequestDTO produto) {
        ProdutoResponseDTO produtoSalvo = service.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @PathVariable Long id,
            @RequestBody CadastrarProdutoRequestDTO produtoRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarProduto(id, produtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoDeletadoResponseDTO> deletarProduto(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deletarProduto(id));
    }

    @PutMapping("/atualizar-quantidade/{produtoId}")
    public ResponseEntity<Produto> atualizarQuantidadeProduto(
        @PathVariable Long produtoId,
        @RequestBody AtualizarQuantidadeDTO body
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarQuantidadeProduto(produtoId, body.quantidade()));
    }

    @PutMapping("/pedido-cancelado/{produtoId}")
    public ResponseEntity<Produto> atualizarProdutoPedidoCancelado(
        @PathVariable Long produtoId,
        @RequestBody AtualizarQuantidadeDTO body
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarProdutoPedidoCancelado(produtoId, body.quantidade()));
    }
}
