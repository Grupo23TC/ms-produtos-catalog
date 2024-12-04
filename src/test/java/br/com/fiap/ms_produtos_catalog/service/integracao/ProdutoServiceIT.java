package br.com.fiap.ms_produtos_catalog.service.integracao;

import br.com.fiap.ms_produtos_catalog.dto.request.AtualizarQuantidadeDTO;
import br.com.fiap.ms_produtos_catalog.dto.request.CadastrarProdutoRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoDeletadoResponseDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.exception.ProdutoNotFoundException;
import br.com.fiap.ms_produtos_catalog.model.Produto;
import br.com.fiap.ms_produtos_catalog.service.ProdutoService;
import br.com.fiap.ms_produtos_catalog.service.ProdutoServiceImpl;
import br.com.fiap.ms_produtos_catalog.utils.ProdutoUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProdutoServiceIT {

    @Autowired
    private ProdutoService produtoService;

    @Test
    void devePermitirCadastrarProduto() {
        CadastrarProdutoRequestDTO request = ProdutoUtil.gerarCadastrarProdutoRequest();

        ProdutoResponseDTO produtoCriado = produtoService.cadastrarProduto(request);

        Assertions.assertThat(produtoCriado)
                .isNotNull()
                .isInstanceOf(ProdutoResponseDTO.class);
    }

    @Nested
    class DeletarProduto {
        @Test
        void devePermitirExcluirProduto() {
            Long id = 4L;

            ProdutoDeletadoResponseDTO response = produtoService.deletarProduto(id);

            Assertions.assertThat(response)
                    .isNotNull()
                    .isInstanceOf(ProdutoDeletadoResponseDTO.class);

            Assertions.assertThat(response.produtoDeletado())
                    .isTrue();
        }

        @Test
        void deveGerarExcecao_QuandoExcluirProduto_IdProdutoNaoEncontrado() {
            Long id = 100000L;

            Assertions.assertThatThrownBy(() -> produtoService.deletarProduto(id))
                    .isNotNull()
                    .isInstanceOf(ProdutoNotFoundException.class)
                    .hasMessage("Produto com id: " + id + " não encontrado");

        }
    }

    @Test
    void deveRetornarProduto_QuandoBuscarPorId_ComSucesso() {
        Long produtoId = 1L;

        ProdutoResponseDTO response = produtoService.buscarProdutosPorId(produtoId);

        assertThat(response)
                .isNotNull()
                .isInstanceOf(ProdutoResponseDTO.class);
    }

    @Test
    void deveLancarProdutoNotFoundException_QuandoProdutoNaoExistir() {
        Long produtoId = 10000L;


        assertThatThrownBy(() -> produtoService.buscarProdutosPorId(produtoId))
                .isNotNull()
                .isInstanceOf(ProdutoNotFoundException.class)
                .hasMessage("Produto com id: " + produtoId + " não encontrado");
    }

    @Test
    void deveAtualizarQuantidade_ComSucesso() {
        Long produtoId = 2L;
        AtualizarQuantidadeDTO request = new AtualizarQuantidadeDTO(5);

        Produto response = produtoService.atualizarQuantidadeProduto(produtoId, request.quantidade());

        assertThat(response)
                .isNotNull()
                .isInstanceOf(Produto.class);
    }

    @Test
    void devePermitirAtualizarProduto() {
        Long id = 1L;
        CadastrarProdutoRequestDTO request = ProdutoUtil.gerarCadastrarProdutoRequest();

        ProdutoResponseDTO produtoAtualizado = produtoService.atualizarProduto(id, request);

        Assertions.assertThat(produtoAtualizado)
            .isNotNull()
            .isInstanceOf(ProdutoResponseDTO.class);

        Assertions.assertThat(produtoAtualizado.id())
            .isEqualTo(id);
    }

}
