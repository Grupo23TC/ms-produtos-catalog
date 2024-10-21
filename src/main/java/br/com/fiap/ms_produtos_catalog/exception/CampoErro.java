package br.com.fiap.ms_produtos_catalog.exception;

public record CampoErro(
        String campo,
        String mensagem
) {
}
