# DesafioTecnico
<h1 align="center"> CRUD usando API's com spring boot </h1>

## Feito com
* [Java](#Título-e-Imagem-de-capa)
* [SpringBoot](#badges)

## Pré-requisitos
* [Java 8 SDK ou Superior](#Título-e-Imagem-de-capa)
* [Maven 4.0.0](#Título-e-Imagem-de-capa)

## Build and Run

1. Clone o repositório

    ``` git clone https://github.com/HiegoGeorge/DesafioTecnico.git```
 
 2. Depois que realizar o clone, na sua IDE execute os comandos

    ``` mvn clean package ``` 
    
 ## Estrutura do projeto
  
* Entities  -> É uma classe que representa o modelo da aplicação
 
* Repository -> traduz um objeto java para uma tabela no banco de dados

     ``` ‘JpaRepository’, que permite executar as principais operações de acesso a uma base de dados de modo automático.```

* Service -> Onde fica toda a logica de negócio de um projeto

     ``` A lógica da minha aplicação isolada e organizada em um só local, para que ela seja de fácil manutenção, e possa ser facilmente acessada por outros componentes.```

* Rest/Resource ->  Onde são realizadas a chamada e configurada as API's 

    ``` É possível gerenciar os verbos HTTP (GET, POST, PUT, DELETE,...) para cada método da classe, permitindo criar todos os acessos Restful para a sua API.```
    
* Swagger -> Utilizado para documentar api's. Ao executar o projeto no profile local, vá ate o navegador é digite: 
 '''http://localhost:8080/swagger-ui/index.html''' , assim poderá observar as api's que o projeto está usando e  exportar para o POSTMAN.
