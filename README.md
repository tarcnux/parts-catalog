
# parts-catalog
Aplicação web para controlar o catálogo de peças (parts catalog) vendidas por uma empresa. Permitindo cadastrar, listar e apagar peças do catálogo.

Aplicação CRUD Fullstack em Java Spring Boot + Vue.js. 
O Back-end usa Spring Boot com Spring Web MVC para REST Controller e Spring Data JPA para interagir com o banco de dados PostgreSQL. 
O Front-end é feito com Vue & Vue Router.

Recursos disponíveis na API
 - Cadastrar nova peça
 - Busca de peças em ordem alfabética por nome
 - Busca de peça por id 
 - Busca de peças por palavra chave no nome
 - Busca de peças por palavra chave no veículo de aplicação
 - Atualizar peça
 - Deletar peça
 - Deletar todas peças
 
O CRUD usa um ambiente de testes unitários com **JUnit** configurado acessando o **banco de dados H2**, usa **Maven** como gerenciador de dependência, e **Java 11** como linguagem.

Ambiente de desenvolvimento **STS 4 - Spring Tool Suite 4** e banco de dados **PostgreSQL**

# Back-end Java Spring Boot
##  :dizzy: Como Executar

- ### **Pré-requisitos**

  - É **necessário** possuir o **[Java 11](https://www.java.com/)** .
  - É **necessário** possuir o **[Git](https://git-scm.com/)** instalado e configurado no computador.
  - É **recomendável** utilizar **[IDE Spring Tools Suite 4](https://spring.io/tools)** .

1. Faça um clone do repositório:
```
  #  Crie um novo Workspace, por exemplo uma pasta chamada: 
     ws-github-teste
  
  $ mkdir ws-github-teste
  $ cd ws-github-teste
  $ git clone https://github.com/tarcnux/parts-catalog.git
  # Foi criada a pasta parts-catalog
  # cd parts-catalog
  # Dentro há duas pastas:
   - backend
   - frontend
```

2. Executando a Aplicação Back-end:
```
  #  Abra o Spring Tool Suite (STS)
  #  Selecione como Workspace a pasta recém clonada: 
     parts-catalog
  #  Import projects - Maven - Existing Maven Projects
  #  Root Directory: ../parts-catalog/backend
  #  Clicar com o botão direito em src/main/java
  #  Procurar por "Run As" e ir na opção "5 Spring Boot App"
  #  Backend App rodando em:
  - Local:   http://localhost:8080
```
### Swagger http://localhost:8080/swagger-ui.html
![Swagger Validator](https://img.shields.io/swagger/valid/3.0?specUrl=https%3A%2F%2Fraw.githubusercontent.com%2Ftarcnux%2Fparts-catalog%2Fmain%2Fapi-docs-swagger.json)

### Rotas da API -  http://localhost:8080/api/v1
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/6da14daca04e6efcaf2b)

|Métodos	| URLs			| Ações	|
|:---------:|:-------------:|:-----:|
|POST		|/parts		|criar uma nova peça |
|GET		|/parts		|recuperar todas as peças |
|GET		|/parts/:id	|recuperar uma peça por: id |
|PUT		|/parts/:id	|atualizar uma peça por:id |
|DELETE		|/parts/:id	|apagar uma peça por:id |
|DELETE		|/parts		|apagar todas as peças |
|GET		|/parts?name=[keyword]	|encontre todas as peças cujo nome contém a [palavra-chave] |
|GET		|/parts?vehicle=[keyword]	|encontre todas as peças cujo veículo de aplicação contém a [palavra-chave] |

#### Cadastrar uma nova peça
```
	POST /parts
	{
	    "name" : "Polia",
	    "applicationVehicle" : "Fusca",
	    "netWeight": 9.87,
	    "grossWeight": 10.98
	}
```

#### Atualizar uma peça
```
	PUT /parts/1
	{
	    "name" : "Atualizado Polia",
	    "applicationVehicle" : "Atualizado Fusca",
	    "netWeight": 9.87,
	    "grossWeight": 10.98
	}	
```
3. Testando a Aplicação
```
  #  Estando no Spring Tool Suite (STS)
  #  Clicar com o botão direito no projeto
     1 - Build Path
     2 - Add Libraries
     3 - JUnit
     4 - Next
     5 - Escolha JUnit 5
     6 - Finish
  #  Clicar com o botão direito em src/test/java
  #  Procurar por "Run As" e ir na opção "4 JUnit Test"
  
```
## Testes Unitários OK
Importante ter a biblioteca do JUnit no Build Path, como descrito acima.

![Testes Unitários Ok](https://github.com/tarcnux/parts-catalog/blob/main/junit5_parts_catalog.png?raw=true)

### E eu feliz com os testes passando todos

![Ambiente de desenvolvimento](https://github.com/tarcnux/parts-catalog/blob/main/tarcnux_dev_environment.jpeg?raw=true)

# Front-end Vue.Js
##  :dizzy: Como Executar
- ### **Pré-requisitos**

  - É **necessário** possuir o **[Node.js® ](https://nodejs.org/pt-br/)** instalado e configurado no computador.
  - É **necessário** possuir o **[NPM](https://nodejs.org/pt-br/)** .
  - É **recomendável** utilizar **[IDE VSCode](https://code.visualstudio.com/)** .

4. Repositório já clonado:
```
  #  O repositório já foi clonado no passo 1 contendo as 
  #	 pastas backend e frontend: 
     $ .../ws-github-teste/parts-catalog
```

5. Executando a Aplicação Front-end:
```
  #  Estando na pasta criada no passo 1
  #  parts-catalog
  $ cd frontend
  #  Instalação das dependências
  $ npm install
  #  Ativa o servidor e o hot-reload
  $ npm run serve
  # Frontend App rodando em:
  - Local:   http://localhost:8081/
```
### Diagrama de Componentes do App Vue.js com o Vue Router e Axios

![Vue js App Component Diagram with Vue Router   Axios](https://user-images.githubusercontent.com/2284408/110176312-d9caeb00-7de1-11eb-866b-e9910f2544ad.png)

### 01 - Tela inicial sem peças cadastradas
![Tela inicial](https://github.com/tarcnux/parts-catalog/blob/main/tela01_sem_parts_cadastradas.png?raw=true)

### 02 - Tela com a listagem de peças cadastradas
 - Listagem em ordem alfabética pelo nome
 - É possível buscar peças pelo nome 
 - É possível buscar peças pelo veículo
 - É possível apagar uma peça ou todas

![Tela de listagem de peças](https://github.com/tarcnux/parts-catalog/blob/main/tela02_listagem_de_parts.png?raw=true)

### 03 - Tela exibindo os detalhes de uma peça selecionada
 - É possível alterar os dados de uma peça selecionada
 
![Tela de detalhes de uma peça](https://github.com/tarcnux/parts-catalog/blob/main/tela03_part_selecionada_exibindo_detalhes.png?raw=true)

### 04 - Dados de uma peça para atualização
 - Campos obrigatórios: nome, peso líquido e peso bruto.
 - Se o peso líquido for maior que o peso bruto a peça não é atualizada e aparece mensagem de erro.
 - Números decimais com 2 casas depois da vírgula.
 - É possível excluir a peça selecionada.
![Tela de atualização](https://github.com/tarcnux/parts-catalog/blob/main/tela04_part_data_to_update.png?raw=true)

### 05 - Tela de cadastro de uma nova peça
 - Campos obrigatórios: nome, peso líquido e peso bruto.
 - Se o peso líquido for maior que o peso bruto a peça não é atualizada e aparece mensagem de erro.
 - Números decimais com 2 casas depois da vírgula.

![Tela de cadastro de uma nova peça](https://github.com/tarcnux/parts-catalog/blob/main/tela05_add_new_part.png?raw=true)

## :memo: ToDo

 - [ ] Criar uma segunda entidade **vehicle** no banco de dados e fazer o relacionamento entre elas. Ficaria a entidade **part** com um relacionamento de NxN com vehicle. Uma peça pode pertencer a vários veículos e um veículo pode conter várias peças.
 - [ ] Criar endpoints no backend para manipular o relacionamento entre as entidades `vehicle` e `part`.
 - [ ] Criar novas telas no frontend de manipulação e exibição das entidades `vehicle` e `part`e seus relacionamentos.

## :page_with_curl: Licença

Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
[![Linkedin](https://img.shields.io/badge/Made%20by-Tarcnux-e77924)](https://www.linkedin.com/in/tarcnux) [![Twitter](https://img.shields.io/twitter/follow/tarcnux?style=social)](https://www.twitter.com/tarcnux)

<sub>Projeto desenvolvido inspirado em:  [[Full-stack] Spring Boot + Vue.js: CRUD example](https://bezkoder.com/spring-boot-vue-js-crud-example/)</sub>
<sub>Back-end inspirado em: [Spring Boot, JPA/Hibernate, PostgreSQL example with Maven](https://bezkoder.com/spring-boot-postgresql-example/)</sub>
<sub>Front-end inspirado em: [Vue.js 2 CRUD Application with Vue Router & Axios](https://bezkoder.com/vue-js-crud-app/)</sub>
<sub>Testes unitários inspirados em: [@DataJpaTest example for Spring Data Repositiory Unit Test](https://bezkoder.com/spring-boot-unit-test-jpa-repo-datajpatest/)</sub>
<sub>Como colocar o Swagger no projeto [Spring Boot + Swagger: documentando sua API automaticamente](https://medium.com/@raphaelbluteau/spring-boot-swagger-documentando-sua-api-automaticamente-27903293aeb6) </sub>
