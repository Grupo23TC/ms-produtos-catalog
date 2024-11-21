package br.com.fiap.ms_produtos_catalog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CadastrarProdutoRequestDTO(

        @NotBlank(message = "O nome do produto é obrigatório")
        String nome,

        @NotBlank(message = "A descrição do produto é obrigatória")
        String descricao,

        @NotNull
        @Positive(message = "O valor do produto deve ser positivo")
        BigDecimal valor,

        @NotNull
        @Positive(message = "A quantidade do produto em estoque deve ser positivo")
        int quantidade
){
}
