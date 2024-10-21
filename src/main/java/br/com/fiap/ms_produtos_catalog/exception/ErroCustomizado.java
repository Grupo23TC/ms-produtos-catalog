package br.com.fiap.ms_produtos_catalog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ErroCustomizado {
    private final String erro;
    private final Instant horario;
    private final String rota;
    private final Integer status;
}
