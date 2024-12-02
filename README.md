# ms-produtos-catalog

# Catálogo de Produtos API

### Proposta de projeto Pós Tech FIAP - Tech Challenge - Fase 4

### Tópicos

- [Descrição do projeto](#descrição-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Ferramentas utilizadas](#ferramentas-utilizadas)
- [Acesso ao projeto](#acesso-ao-projeto)
- [Sobre a aplicação](#sobre-a-aplicação)
- [Testes](#testes)
- [Guia de implantação](#guia-de-implantação)
- [Desenvolvedores](#desenvolvedores)

## Descrição do projeto

<p align="justify">
Este projeto apresenta o desenvolvimento de um microsservice que faz o processamento de produtos de um sistema de catálogo de produtos e estoque, além disso, há um módulo para carga de produtos via Spring Batch.

</p>

## Funcionalidades

`Funcionalidade 1:` Cadastrar, Consultar, Alterar e Remover Produtos.

`Funcionalidade 2:` End-point para atualizar a quantidade de produtos em estoque utilizado pela api de Pedidos.

`Funcionalidade 3:` Carga de produtos via arquivo CSV utilizando o Spring Batch, tanto manualmente quanto via Schedule.


## Ferramentas utilizadas
<div style="display: flex; gap: 15px">
<a href="https://www.java.com" target="_blank"> 
    <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="Java" width="40" height="40"/> 
</a>

<a href="https://spring.io/" target="_blank"> 
    <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" alt="Spring" width="40" height="40"/> 
</a>

<a href="https://www.postman.com/" target="_blank"> 
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original.svg" alt="Postman" width="40" /> 
</a>

<a href="https://junit.org/junit5/" target="_blank"> 
    <img src="https://camo.githubusercontent.com/47ab606787e47aee8033b92c8f1d05c0e74b9b81904550f35a8f54e39f6c993b/68747470733a2f2f6a756e69742e6f72672f6a756e6974352f6173736574732f696d672f6a756e6974352d6c6f676f2e706e67" alt="JUnit" width="40" height="40"/> 
</a>

<a href="https://www.postgresql.org/" target="_blank">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-plain.svg" width="40"/>
</a>

<a href="https://www.docker.com/" target="_blank">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-plain.svg" width="40"/>
</a>

</div>


## Acesso ao projeto

Você pode [acessar o código fonte do projeto](https://github.com/Grupo23TC/ms-produtos-catalog).
Documentação swagger: [Swagger](http://localhost:8084/ms-produtos-catalog-doc.html)

## Sobre a aplicação

A aplicação foi arquitetada através dos princípios de Arquitetura MVC e foi projetada para ser consumida como uma API Rest.

## Testes

Adotamos o TDD como metodologia para o desenvolvimento e utilizamos o JUnit como principal ferramenta para os testes
unitários e de integração.

## Guia de implantação

Para rodar o projeto localmente, precisamos do [Docker Hub](https://www.docker.com/) instalado.
Rode o comando: <b><i>"docker-compose up"</i></b>
Confira em seu Docker Desktop a subida do container e suas respectivas aplicações e bancos de dados isolados.

## Desenvolvedores

<table align="center">
  <tr>
    <td align="center">
      <div>
        <img src="https://avatars.githubusercontent.com/davialvs" width="120px;" alt="Foto no GitHub" class="profile"/><br>
          <b> Davi Alves  </b><br>
            <a href="https://www.linkedin.com/in/davi-alves-dev/" alt="Linkedin"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" height="20"></a>
            <a href="https://github.com/davialvs" alt="Github"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" height="20"></a>
      </div>
    </td>
  </tr>
</table>