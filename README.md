# parts-catalog
Aplicação web para controlar o catálogo de peças (parts catalog) vendidas por uma empresa.
Permitindo cadastrar, listar e apagar peças do catálogo.

Aplicação CRUD Fullstack em Java Spring Boot + Vue.js. 
O Back-end usa Spring Boot com Spring Web MVC para REST Controller e Spring Data JPA para interagir com o banco de dados PostgreSQL. 
O Front-end é feito com Vue & Vue Router.

# Rotas da API
|Métodos	| Urls			| Ações	|
|:---------:|:-------------:|:-----:|
|POST		|/api/v1/parts		|criar uma nova peça |
|GET		|/api/v1/parts		|recuperar todas as peças |
|GET		|/api/v1/parts/:id	|recuperar uma peça por: id |
|PUT		|/api/v1/parts/:id	|atualizar uma peça por:id |
|DELETE		|/api/v1/parts/:id	|apagar uma peça por:id |
|DELETE		|/api/v1/parts		|apagar todas as peças |
|GET		|/api/v1/parts?name=[keyword]	|encontre todas as peças cujo nome contém a [palavra-chave] |


[![Linkedin](https://img.shields.io/badge/Made%20by-Tarcnux-e77924)](https://www.linkedin.com/in/tarcnux)

[![Twitter](https://img.shields.io/twitter/follow/tarcnux?style=social)](https://www.twitter.com/tarcnux)
