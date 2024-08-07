## FilmesOn

Bem-vindo ao **FilmesOn**, uma aplicação Spring Boot para gerenciamento de filmes. Com esta aplicação, você pode adicionar, atualizar, deletar e avaliar filmes, além de obter recomendações de filmes ainda não avaliados.

### Funcionalidades

- **Listar todos os filmes:** Obtenha uma lista de todos os filmes cadastrados no sistema.
- **Detalhes do filme:** Obtenha detalhes de um filme específico através do seu ID.
- **Adicionar novo filme:** Cadastre novos filmes com título, descrição e status de avaliação.
- **Atualizar filme:** Atualize as informações de um filme existente.
- **Deletar filme:** Remova um filme do sistema.
- **Recomendar filme não avaliado:** Receba recomendações de filmes que ainda não foram avaliados.
- **Avaliar filme:** Avalie filmes existentes no sistema.

### Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas:

- Java 17 ou superior
- Maven
- IDE de sua escolha (recomendo IntelliJ IDEA ou Eclipse)

### Instalação

1. Clone o repositório para sua máquina local:
   ```bash
   git clone https://github.com/seu-usuario/filmesOn.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd filmesOn/desafio
   ```

3. Compile o projeto com Maven:
   ```bash
   mvn clean install
   ```

4. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

### Uso

Após iniciar a aplicação, você pode acessar a documentação da API e testar as funcionalidades utilizando o Swagger UI, disponível em:

```
http://localhost:8080/swagger-ui/index.html
```

#### Endpoints Disponíveis

1. **Listar todos os filmes**
   ```http
   GET /api/movies
   ```
   Retorna uma lista de todos os filmes cadastrados.

2. **Detalhes do filme**
   ```http
   GET /api/movie/{id}
   ```
   Retorna os detalhes de um filme específico.

3. **Adicionar novo filme**
   ```http
   POST /api/movie/create_movie
   ```
   Adiciona um novo filme ao sistema. Envie um JSON no corpo da requisição com os detalhes do filme.

4. **Atualizar filme**
   ```http
   PUT /api/movie/{id}
   ```
   Atualiza as informações de um filme existente. Envie um JSON no corpo da requisição com os novos detalhes do filme.

5. **Deletar filme**
   ```http
   DELETE /api/movie/{id}
   ```
   Remove um filme do sistema.

6. **Recomendar filme não avaliado**
   ```http
   GET /api/movie/recommend_unreviewed
   ```
   Retorna um filme não avaliado para recomendação.

7. **Avaliar filme**
   ```http
   POST /api/review
   ```
   Avalia um filme existente. Envie os parâmetros `id` e `rating` na requisição.

### Estrutura do Projeto

- **Controllers**: Contém os controladores da aplicação, responsáveis por lidar com as requisições HTTP.
- **Services**: Contém as regras de negócio e a lógica de aplicação.
- **DTO (Data Transfer Objects)**: Contém os objetos de transferência de dados utilizados para comunicação entre as camadas da aplicação.
- **Models**: Contém as entidades de domínio do sistema.
- **Repositories**: Contém os repositórios para acesso ao banco de dados.

### Testes

O projeto inclui testes unitários para os serviços e controladores. Para executar os testes, utilize o comando:

```bash
mvn test
```

### Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

### Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

Aproveite o **FilmesOn** para gerenciar seus filmes favoritos e descobrir novas recomendações! 🎬🍿
