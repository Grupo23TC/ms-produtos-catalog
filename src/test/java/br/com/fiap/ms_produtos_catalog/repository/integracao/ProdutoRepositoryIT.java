package br.com.fiap.ms_produtos_catalog.repository.integracao;

import br.com.fiap.ms_produtos_catalog.model.Produto;
import br.com.fiap.ms_produtos_catalog.repository.ProdutoRepository;
import br.com.fiap.ms_produtos_catalog.utils.ProdutoUtil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProdutoRepositoryIT {
    @Autowired
    private ProdutoRepository repository;

    @Test
    void deveCadastrarProduto() {
        Produto produto = ProdutoUtil.gerarProduto();

        Produto produtoSalvo = repository.save(produto);

        assertThat(produtoSalvo)
                .isNotNull()
                .isInstanceOf(Produto.class)
                .isEqualTo(produto);

        assertThat(produtoSalvo.getId())
                .isEqualTo(produto.getId());
    }

    @Nested
    class BuscarProdutos {
        @Test
        void deveListarProdutosPaginado() {
            PageRequest pageRequest = PageRequest.of(0, 10);

            Page<Produto> produtos = repository.findAll(pageRequest);

            assertThat(produtos)
                    .isNotNull()
                    .isInstanceOf(Page.class)
                    .isNotEmpty()
                    .hasSize(10);
        }

        @Test
        void deveBuscarProdutoPorId() {
            Long id = 1L;

            Optional<Produto> produto = repository.findById(id);

            assertThat(produto)
                    .isNotNull()
                    .isPresent();

            assertThat(produto.get().getId())
                    .isEqualTo(id);
        }

        @Test
        void deveRetornarVazio_QuandoBuscarProdutoPorId_IdProdutoNaoEncontrado() {
            Long id = 100L;

            Optional<Produto> produto = repository.findById(id);

            assertThat(produto)
                    .isEmpty();
        }
    }

    @Test
    void deveExcluirProduto() {
        Long id = 5L;

        repository.deleteById(id);

        Optional<Produto> produto = repository.findById(id);

        assertThat(produto)
                .isNotPresent();
    }
}
