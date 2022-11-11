# Golden Raspberry Awards - API de Pior Filme
API RESTful com indicadores da pior categoria de filme do Golden Raspberry Awards.

## Sobre o Project
O objetivo desta API é ler uma fonte de dados CSV, armazenar os dados na memória e fornecer o produtor com maior intervalo entre dois prêmios consecutivos, e aquele que obteve dois prêmios mais rápido.

### Recurso de API
**Request**

**Method:** GET

**URL:** /goldenraspberryawards-api/v1/produtor/premios

**Exemplo de resposta:**
```javascript
{
	"min": [{
		"producer": "Charles B. Wessler",
		"interval": 8,
		"previousWin": 1995,
		"followingWin": 2013
	}],
	"max": [{
		"producer": "Ozzie Areu",
		"interval": 6,
		"previousWin": 2013,
		"followingWin": 2019
	}]
}
```

### Construído com
* [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/) - The IDE used
* [Java 11](https://www.java.com/pt-BR/) - Execution platform
* [Spring Boot - 2.4.4](https://spring.io/projects/spring-boot) - Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run"
* [H2 Database](https://www.h2database.com/html/main.html) - In memory database
* [Project Lombok](https://projectlombok.org/) - Repetitive code reduction
* [JUnit](https://junit.org/junit5/) - Unit and integration tests
* [Maven](https://maven.apache.org/) - Build automation tool

## Começando
Para subir a aplicação corretamente, siga estes passos.

### Pre-requisitos
Este projeto requer Java 11 ou superior e use um IDE de sua escolha que suporte Maven.

### Instalação
Clone o Repositório
```
git clone https://github.com/Mestreluck/goldenraspberryawards
```

## Executando
Abra o projeto em seu IDE preferido e execute a classe principal **GoldenRaspberryAwardsApplication**.

Use o Postman, ou seu navegador para chamar a api:
```
curl http://localhost:8000/goldenraspberryawards-api/v1/produtor/premios
```

### Testes
Execute a classe **GoldenRaspberryAwardsApplicationTests** e verifique se está tudo bem.