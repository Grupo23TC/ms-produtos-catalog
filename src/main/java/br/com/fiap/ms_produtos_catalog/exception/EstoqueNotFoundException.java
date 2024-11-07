package br.com.fiap.ms_produtos_catalog.exception;

public class EstoqueNotFoundException extends RuntimeException{
    public EstoqueNotFoundException(String msg) {
        super(msg);
    }
}
