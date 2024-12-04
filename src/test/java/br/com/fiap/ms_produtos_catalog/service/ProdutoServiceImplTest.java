package br.com.fiap.ms_produtos_catalog.service;

import br.com.fiap.ms_produtos_catalog.dto.request.CadastrarProdutoRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoDeletadoResponseDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.exception.ProdutoNotFoundException;
import br.com.fiap.ms_produtos_catalog.model.Produto;
import br.com.fiap.ms_produtos_catalog.utils.ProdutoUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoServiceImplTest {

    @Mock
    private ProdutoService service;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveListarProdutosPaginados() {
        ProdutoResponseDTO produto1 = ProdutoUtil.gerarProdutoResponse();
        ProdutoResponseDTO produto2 = ProdutoUtil.gerarProdutoResponse();
        ProdutoResponseDTO produto3 = ProdutoUtil.gerarProdutoResponse();
        List<ProdutoResponseDTO> listaDeProdutos = new ArrayList<>(Arrays.asList(produto1, produto2, produto3));

        Pageable pageRequest = PageRequest.of(0, 10);
        Page<ProdutoResponseDTO> produtosPaginado = new PageImpl<>(listaDeProdutos, pageRequest, listaDeProdutos.size());

        when(service.listarProdutos(any(Pageable.class))).thenReturn(produtosPaginado);

        Page<ProdutoResponseDTO> pedidos = service.listarProdutos(pageRequest);

        assertThat(pedidos)
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(Page.class)
                .hasSize(listaDeProdutos.size());

        assertThat(pedidos.getContent())
                .containsExactlyElementsOf(listaDeProdutos);

        verify(service, times(1)).listarProdutos(any(Pageable.class));
    }

    @Test
    void deveBuscarProdutoPorId() {
        ProdutoResponseDTO produto = ProdutoUtil.gerarProdutoResponse();

        when(service.buscarProdutosPorId(any(Long.class))).thenReturn(produto);

        ProdutoResponseDTO produtoBuscado = service.buscarProdutosPorId(produto.id());

        assertThat(produtoBuscado)
                .isNotNull()
                .isInstanceOf(ProdutoResponseDTO.class);

        assertThat(produtoBuscado.id())
                .isEqualTo(produto.id());

        verify(service, times(1)).buscarProdutosPorId(anyLong());
    }

    @Test
    void deveGerarExcecao_QuandoBuscarProdutoPorId_IdNaoEncontrado() {
        Long id = 1L;

        when(service.buscarProdutosPorId(anyLong()))
                .thenThrow(new ProdutoNotFoundException("Produto com id: " + id + " não encontrado"));

        assertThatThrownBy(() -> service.buscarProdutosPorId(id))
                .isNotNull()
                .isInstanceOf(ProdutoNotFoundException.class)
                .hasMessage("Produto com id: " + id + " não encontrado");
    }

    @Nested
    class DeletarProduto {
        @Test
        void devePermitirExcluirProduto() {
            Long id = 1L;
            ProdutoDeletadoResponseDTO produto = ProdutoUtil.gerarProdutoDeletadoResponse();

            when(service.deletarProduto(any(Long.class))).thenReturn(produto);

            ProdutoDeletadoResponseDTO response = service.deletarProduto(id);

            assertThat(response)
                    .isNotNull()
                    .isInstanceOf(ProdutoDeletadoResponseDTO.class);

            assertThat(response.produtoDeletado())
                    .isTrue();

            verify(service, times(1)).deletarProduto(any(Long.class));
        }

        @Test
        void deveGerarExcecao_QuandoExcluirProduto_IdProdutoNaoEncontrado() {
            Long id = 1L;

            when(service.deletarProduto(any(Long.class)))
                    .thenThrow(new ProdutoNotFoundException("Produto com id: " + id + " não encontrado"));

            assertThatThrownBy(() -> service.deletarProduto(id))
                    .isNotNull()
                    .isInstanceOf(ProdutoNotFoundException.class)
                    .hasMessage("Produto com id: " + id + " não encontrado");
        }
    }

    @Test
    void devePermitirCadastrarProduto() {
        CadastrarProdutoRequestDTO request = ProdutoUtil.gerarCadastrarProdutoRequest();
        ProdutoResponseDTO produto = ProdutoUtil.gerarProdutoResponse();

        when(service.cadastrarProduto(any(CadastrarProdutoRequestDTO.class))).thenReturn(produto);

        ProdutoResponseDTO produtoCriado = service.cadastrarProduto(request);

        assertThat(produtoCriado)
                .isNotNull()
                .isInstanceOf(ProdutoResponseDTO.class);

        verify(service, times(1)).cadastrarProduto(any(CadastrarProdutoRequestDTO.class));
    }

    @Test
    void deveAtualizarQuantidadeProduto(){
        Produto produto = ProdutoUtil.gerarProduto();
        int quantidade = 2;
        when(service.atualizarQuantidadeProduto(1L, quantidade)).thenReturn(produto);

        Produto request = service.atualizarQuantidadeProduto(1L, quantidade);

        assertThat(request)
                .isNotNull()
                .isInstanceOf(Produto.class);

        assertThat(request.getQuantidadeEstoque())
                .isEqualTo(produto.getQuantidadeEstoque());

        verify(service, times(1)).atualizarQuantidadeProduto(1L, quantidade);
    }

    @Test
    void deveAtualizarProdutoPedidoCancelado(){
        Produto produto = ProdutoUtil.gerarProduto();
        int quantidade = 2;
        when(service.atualizarProdutoPedidoCancelado(1L, quantidade)).thenReturn(produto);

        Produto request = service.atualizarProdutoPedidoCancelado(1L, quantidade);

        assertThat(request)
                .isNotNull()
                .isInstanceOf(Produto.class);

        assertThat(request.getQuantidadeEstoque())
                .isEqualTo(produto.getQuantidadeEstoque());

        verify(service, times(1)).atualizarProdutoPedidoCancelado(1L, quantidade);
    }

    @Test
    void devePermitirAtualizarProduto() {
        Long id = 1L;
        ProdutoResponseDTO produto = ProdutoUtil.gerarProdutoResponse();
        CadastrarProdutoRequestDTO request = ProdutoUtil.gerarCadastrarProdutoRequest();

        when(service.atualizarProduto(any(Long.class), any(CadastrarProdutoRequestDTO.class))).thenReturn(produto);

        ProdutoResponseDTO produtoAtualizado = service.atualizarProduto(id, request);

        assertThat(produtoAtualizado)
            .isNotNull()
            .isInstanceOf(ProdutoResponseDTO.class);

        assertThat(produtoAtualizado.id())
            .isEqualTo(id);

        verify(service, times(1)).atualizarProduto(any(Long.class), any(CadastrarProdutoRequestDTO.class));
    }
}