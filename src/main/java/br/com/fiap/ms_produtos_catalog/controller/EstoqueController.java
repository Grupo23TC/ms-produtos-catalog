package br.com.fiap.ms_produtos_catalog.controller;

import br.com.fiap.ms_produtos_catalog.dto.request.InserirEstoqueRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.EstoqueResponseDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.model.Produto;
import br.com.fiap.ms_produtos_catalog.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping
    public ResponseEntity<Page<EstoqueResponseDTO>> listarEstoque(
            @PageableDefault(page = 0, size = 20) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarEstoque(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> buscarEstoquePorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarEstoquePorId(id));
    }

    @PostMapping
    public ResponseEntity<EstoqueResponseDTO> InserirProdutoNoEstoque(@RequestBody InserirEstoqueRequestDTO estoque) {
        EstoqueResponseDTO produtoSalvoNoEstoque = service.inserirProdutoNoEstoque(estoque);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvoNoEstoque);
    }

    @PutMapping("/decrementar/{produtoId}") //TODO talvez ser um atualizar com uma condicional pra decrementar ou incrementar
    public ResponseEntity<Produto> decrementarEstoque(
            @PathVariable Long produtoId,
            @RequestBody int quantidade
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.decrementarEstoque(produtoId, quantidade));
    }
}
