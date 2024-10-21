package br.com.fiap.ms_produtos_catalog.exception;

import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidacaoErro extends ErroCustomizado {

    private final List<CampoErro> erros = new ArrayList<>();

    public ValidacaoErro(String erro, Instant horario, String rota, Integer status){
        super(erro, horario, rota, status);
    }

    public void addCampoErro(String campo, String mensagem){
        erros.removeIf(erros -> erros.campo().equals(campo));
        erros.add(new CampoErro(campo, mensagem));
    }
}
