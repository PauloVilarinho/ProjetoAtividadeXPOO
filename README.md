# ProjetoAtividadeXPOO
Projeto desenvolvido para projeto final da matéria de POO no IFPI teresina central

# MyVideoGameList
Aplicativo para criar uma comunidade de reviews de jogos onde as pessoas podem expressar suas opiniões a cerca dos jogos que já jogaram enquanto podem fazer suas proprias listas de jogos preferidos.

## Classes Modelo
- **User**
  - Atributos
    - id;
    - username;
    - password;
  - Métodos
    - getters e setters.
- **GameList**
  - Atributos
    - id;
    - title;
    - user;
  - Métodos
    - getters e setters.
- **List/Game**
  - Atributos
    - id
    - Game
    - GameList
  - Métodos
    - getters e setters.
- **Game**
  - Atributos
    - id
    - title
    - description
  - Métodos
    - getters e setters.
- **Review**
  - Atributos
    - reviewText
    - score
    - Game
    - User
  - Métodos
    - getters e setters.
## Relacionamentos
O usuário ao entrar no aplicativo tem a capacidade de criar suas listas de jogos, os separando e organizando-as em categorias que desejar. Alem disso todos os usuários podem deixar reviews nos jogos para expor seus pensamentos sobre o jogo para o resto da comunidade
## Persistência de dados
Atualmente utilizando object box.
## Diagrama de Classe simplificado
## Funcionalidades
- Cadastrar novo usuário ou entrar em uma conta já existente;
- Criar, editar e excluir uma lista;
- Criar, editar e excluir um jogo;
- Criar, editar e excluir um review;
- adicionar e remover jogos de uma lista;
## Requisitos e Restrições
- Aplicativo desenvolvido utilizando linguagem de programação Java;
- Desenvolvido para no minimo sdk 23 do android.
## Storyboard de navegação


