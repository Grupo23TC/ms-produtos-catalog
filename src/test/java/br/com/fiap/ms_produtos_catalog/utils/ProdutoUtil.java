package br.com.fiap.ms_produtos_catalog.utils;

import br.com.fiap.ms_produtos_catalog.dto.request.CadastrarProdutoRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoDeletadoResponseDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.model.Produto;

import java.math.BigDecimal;

public class ProdutoUtil {
    public static Produto gerarProduto(){
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("TV");
        produto.setValor(new BigDecimal("2000.00"));
        produto.setDescricao("Televisor");
        produto.setQuantidadeEstoque(200);
        return produto;
    }

    public static ProdutoResponseDTO gerarProdutoResponse(){
        return new ProdutoResponseDTO(
            1L,
            "TV",
            "Televisor",
            200, new BigDecimal("2000.00")
        );
    }

    public static ProdutoDeletadoResponseDTO gerarProdutoDeletadoResponse() {
        return new ProdutoDeletadoResponseDTO(
                true
        );
    }

    public static CadastrarProdutoRequestDTO gerarCadastrarProdutoRequest() {
        return new CadastrarProdutoRequestDTO(
                "TV",
                "Televisor",
                new BigDecimal("2000.00"),
                200
        );
    }
}
