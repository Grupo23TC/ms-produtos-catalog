package br.com.fiap.ms_produtos_catalog.controller;

import br.com.fiap.ms_produtos_catalog.dto.request.AtualizarQuantidadeDTO;
import br.com.fiap.ms_produtos_catalog.dto.request.CadastrarProdutoRequestDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoDeletadoResponseDTO;
import br.com.fiap.ms_produtos_catalog.dto.response.ProdutoResponseDTO;
import br.com.fiap.ms_produtos_catalog.exception.ProdutoNotFoundException;
import br.com.fiap.ms_produtos_catalog.exception.handler.ControllerExceptionHandler;
import br.com.fiap.ms_produtos_catalog.model.Produto;
import br.com.fiap.ms_produtos_catalog.service.ProdutoService;
import br.com.fiap.ms_produtos_catalog.utils.ProdutoUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProdutoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProdutoService produtoService;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        ProdutoController controller = new ProdutoController(produtoService);
        PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver();
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(pageableResolver)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                })
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveListarProdutos() throws Exception {
        var produto = ProdutoUtil.gerarProdutoResponse();
        var pageable = PageRequest.of(0, 10);
        var page = new PageImpl<>(Collections.singletonList(produto), pageable, 1);

        when(produtoService.listarProdutos(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(
                        get("/produtos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("page", "0")
                                .param("size", "10"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void buscarProdutoPorId() throws Exception {
        Long id = 1L;
        ProdutoResponseDTO response = ProdutoUtil.gerarProdutoResponse();
        when(produtoService.buscarProdutosPorId(any(Long.class))).thenReturn(response);

        mockMvc.perform(get("/produtos/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarNotFound_QuandoProdutoNaoEncontrado() throws Exception {
        when(produtoService.buscarProdutosPorId(eq(1L))).thenThrow(new ProdutoNotFoundException("Produto não encontrado"));

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveCadastrarProduto() throws Exception {
        ProdutoResponseDTO response = ProdutoUtil.gerarProdutoResponse();
        when(produtoService.cadastrarProduto(any(CadastrarProdutoRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nome\":null,\"quantidadeEmEstoque\":null,\"descricao\":null,\"valor\":null}")
                )
                .andExpect(status().isCreated());
    }

    @Test
    void deveAtualizarQtdeProduto() throws Exception {
        Produto response = ProdutoUtil.gerarProduto();
        when(produtoService.atualizarQuantidadeProduto(1L,200)).thenReturn(response);

        mockMvc.perform(put("/produtos/atualizar-quantidade/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nome\":null,\"quantidadeEmEstoque\":null,\"descricao\":null,\"valor\":null}")
                )
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarProdutoPedidoCancelado() throws Exception {
        Produto response = ProdutoUtil.gerarProduto();
        when(produtoService.atualizarProdutoPedidoCancelado(1L,200)).thenReturn(response);

        mockMvc.perform(put("/produtos/pedido-cancelado/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nome\":null,\"quantidadeEmEstoque\":null,\"descricao\":null,\"valor\":null}")
                )
                .andExpect(status().isOk());
    }

    @Test
    void deletarProduto() throws Exception {
        ProdutoDeletadoResponseDTO response = ProdutoUtil.gerarProdutoDeletadoResponse();
        when(produtoService.deletarProduto(eq(1L))).thenReturn(response);

        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isOk());
    }


}