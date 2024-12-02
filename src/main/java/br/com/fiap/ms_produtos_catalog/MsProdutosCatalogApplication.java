package br.com.fiap.ms_produtos_catalog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Catálogo de Produtos - Grupo 23",
		version = "1.0.0",
		description = "Sistema de catálogo de produtos desenvolvido para o Tech Challenge da 4ª fase da Pós Tech da FIAP."
))
public class MsProdutosCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProdutosCatalogApplication.class, args);
	}

}
