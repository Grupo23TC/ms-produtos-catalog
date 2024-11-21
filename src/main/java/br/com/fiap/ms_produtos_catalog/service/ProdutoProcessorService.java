package br.com.fiap.ms_produtos_catalog.service;

import br.com.fiap.ms_produtos_catalog.model.Produto;
import org.springframework.batch.item.ItemProcessor;

public class ProdutoProcessorService implements ItemProcessor<Produto, Produto> {

    @Override
    public Produto process(Produto item) throws Exception {
        return item;
    }
}
