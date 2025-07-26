
# Projeto e-commerce materiais escolares para pais com filhos em idade escolar

Projeto fictício de API para e-commerce demonstração da implementação de uma API feita em modelo arquitetural MVC

## Pré-requisitos

- Java 17
- Docker Compose version v2.35.1
- Apache Maven 3.8.7

## Instalação

- Clonar repositório em local de sua preferência:
```bash
git clone https://github.com/lucasDomaradzki/meu-ecommerce-escolar.git
```
- Realizar instalação/build da aplicação onde foi clonado o código:
```bash
mvn clean install
```
- Rodando a API pela primeira vez com comando docker compose para iniciar a aplicação:
```bash
docker compose up -d
```

- Parando docker e aplicação:
```bash
docker compose stop
```

- Reiniciando docker e aplicação:
```bash
docker compose start
```


## Utilização

### Accesando  Swagger
Para accesar o swagger abra em seu browser na página:
http://localhost:8080/swagger-ui.html

![alt text](https://github.com/user-attachments/assets/6c9605f7-f8bf-4d5a-9bc2-b543b6dbd2a6)

### Accesando  Banco de dados
O banco de dados MySQL está exposto com os seguintes detalhes:
- Nome do banco: e_commerce
- Usuário: e_commerce
- Senha: e_commerce
- Porta: 3306
- Host: e-commerce-mysql

O modo mais fácil de acessar o banco de dados é através dos comandos abaixo:

Permite acessar o container mysql
```
docker exec -it $(docker ps | grep mysql | awk '{print $1}') sh
```

Ou simplesmente execute o seu comando, sua query com o comando abaixo:
```
docker exec $(docker ps | grep mysql | awk '{print $1}') mysql -vvv -u e_commerce -p'e_commerce' e_commerce -e 'select * from product'
```
Exemplo:
```
lucasdom@lucas-domaradzki:~$ docker exec $(docker ps | grep mysql | awk '{print $1}') mysql -vvv -u e_commerce -p'e_commerce' e_commerce -e 'select * from grade'
mysql: [Warning] Using a password on the command line interface can be insecure.
--------------
select * from grade
--------------

+----+--------------------------------------+----------------------+-----------+
| id | uuid                                 | name                 | school_id |
+----+--------------------------------------+----------------------+-----------+
|  1 | 56b3073c-659c-11f0-b5fd-6af5bbc8d643 | Maternal I A         |         3 |
|  2 | 56b318db-659c-11f0-b5fd-6af5bbc8d643 | 1 Ano Fundamental B  |         1 |
|  3 | 56b31caa-659c-11f0-b5fd-6af5bbc8d643 | 9 Ano Fundamental C  |         1 |
|  4 | 56b31ed9-659c-11f0-b5fd-6af5bbc8d643 | 1 Ano Ensino Medio D |         2 |
+----+--------------------------------------+----------------------+-----------+
4 rows in set (0.00 sec)

Bye
```
### Accesando  log da API
```
docker compose logs -f e-commerce-api
```
### Parando os containers
```
docker stop $(docker ps | awk '{print $1}' | tail -n 2)
```
### Reiniciando os containers
```
docker start $(docker ps | awk '{print $1}' | tail -n 2)
```
**Informação importante: todos os comandos docker devem ser executados na pasta root do projeto ou do repositório que foi clonado, pois os comandos docker compose precisam estar no mesmo nível onde se encontra o aqui docker-compose.yml**

## License

[MIT](https://choosealicense.com/licenses/mit/)
