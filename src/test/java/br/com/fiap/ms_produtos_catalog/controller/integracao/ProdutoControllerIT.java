package br.com.fiap.ms_produtos_catalog.controller.integracao;

import br.com.fiap.ms_produtos_catalog.utils.ProdutoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveListarProdutos() {
        given()
                .param("page", 0)
                .param("size", 10)
                .when()
                .get("/produtos")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void deveSalvarProduto() {
        given()
                .contentType(ContentType.JSON)
                .body(ProdutoUtil.gerarCadastrarProdutoRequest())
                .when()
                .post("/produtos")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void deveDeletarProduto() {
        given()
                .when()
                .delete("/produtos/{id}", 3)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/produtoDeletado.schema.json"));

    }

    @Test
    void deveBuscarProdutoPorId() {
        given()
                .when()
                .get("/produtos/{id}", 1)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void deveRetornarNotFound_QuandoProdutoNaoEncontrado() {
        given()
                .when()
                .get("/produtos/{id}", 999)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deveRetornarErrosDeValidacao_QuandoPayloadForInvalido() {
        String payloadInvalido = """
                {
                  "nome": "",
                  "descricao": "",
                  "valor": -100.00,
                  "quantidade": -10
                }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(payloadInvalido)
            .when()
            .post("/produtos")
            .then()
            .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .body(matchesJsonSchemaInClasspath("schemas/erroPayload.schema.json"));
    }
}
