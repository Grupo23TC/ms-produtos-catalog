package br.com.fiap.ms_produtos_catalog.repository;

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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoRepositoryTest {
    @Mock
    private ProdutoRepository repository;
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
    void deveCadastrarProduto() {
        Produto produto = ProdutoUtil.gerarProduto();

        when(repository.save(any(Produto.class))).thenAnswer(answer -> answer.getArgument(0));

        Produto produtoSalvo = repository.save(produto);

        assertThat(produtoSalvo)
                .isNotNull()
                .isInstanceOf(Produto.class)
                .isEqualTo(produto);

        assertThat(produtoSalvo.getId())
                .isEqualTo(produto.getId());

        verify(repository, times(1)).save(any(Produto.class));
    }

    @Nested
    class BuscarProdutos {
        @Test
        void deveListarProdutosPaginado() {
            Produto produto1 = ProdutoUtil.gerarProduto();
            Produto produto2 = ProdutoUtil.gerarProduto();
            Produto produto3 = ProdutoUtil.gerarProduto();

            List<Produto> listaDeProdutos = new ArrayList<>(Arrays.asList(produto1, produto2, produto3));

            Pageable pageRequest = PageRequest.of(0, 10);
            Page<Produto> produtosPaginado = new PageImpl<>(listaDeProdutos, pageRequest, listaDeProdutos.size());

            when(repository.findAll(any(Pageable.class))).thenReturn(produtosPaginado);

            Page<Produto> produtos = repository.findAll(pageRequest);

            assertThat(produtos)
                    .isNotNull()
                    .isNotEmpty()
                    .isInstanceOf(Page.class)
                    .hasSize(listaDeProdutos.size());

            assertThat(produtos.getContent())
                    .containsExactlyElementsOf(listaDeProdutos);

            verify(repository, times(1)).findAll(pageRequest);
        }

        @Test
        void deveBuscarProdutoPorId() {
            Produto produto = ProdutoUtil.gerarProduto();

            when(repository.findById(any(Long.class))).thenReturn(Optional.of(produto));

            Optional<Produto> produtoOptional = repository.findById(produto.getId());

            assertThat(produtoOptional)
                    .isNotNull()
                    .isPresent()
                    .isInstanceOf(Optional.class);

            assertThat(produtoOptional.get().getId())
                    .isEqualTo(produto.getId());

            verify(repository, times(1)).findById(anyLong());
        }

        @Test
        void deveRetornarVazio_QuandoBuscarProdutoPorId_IdProdutoNaoEncontrado() {
            when(repository.findById(any(Long.class))).thenReturn(Optional.empty());

            Optional<Produto> produtoOptional = repository.findById(1L);

            assertThat(produtoOptional)
                    .isEmpty();

            verify(repository, times(1)).findById(anyLong());
        }

    }

    @Test
    void deveExcluirProduto() {
        Long id = 1L;

        doNothing().when(repository).deleteById(any(Long.class));

        repository.deleteById(id);

        verify(repository, times(1)).deleteById(anyLong());
    }
}