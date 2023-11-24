
# API para o aplicativo de delivery em JAVA SWING


## Requisitos

- [PostgreSQL](https://www.postgresql.org/download/)
- [JAVA 17+](https://www.java.com/pt-BR/)

## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/Diegojacober/EatEasy-API.git
```

Entre no diretório do projeto

```bash
  cd EatEasy-API
```

Configure o banco de dados, entre em src\main\resources e edite o arquivo application.yml

```bash
  datasource:
    url: jdbc:postgresql://localhost:5423/NOME_DO_BANCO
    username: SEU_USUARIO
    password: SUA_SENHA
```


Rode o arquivo src\main\java\com\diegojacober\eateasyapi\EateasyApiApplication.java

