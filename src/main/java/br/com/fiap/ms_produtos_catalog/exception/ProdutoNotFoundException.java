package br.com.fiap.ms_produtos_catalog.exception;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(String msg) {
        super(msg);
    }
}
