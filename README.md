# Cook And Laugh / Варити І Реготати
## Prerequisites
You have to have installed:
* Docker
* JVM 22

## Run application
Execute command:
```shell
./mvnw spring-boot:run
```

## Tests
To run tests execute command:
```shell
./mvnw test
```

## Swagger
To open swagger go to
`http://localhost:8080/swagger-ui/index.html`

## Liquibase migrations
⚠️ Migrations are managed by spring, and 'update' is running automatically. Therefore manual rollbacks won't work.
To make them work it's needed to disable `spring.liquibase.enabled` in `application.yaml`, to manage everything manually.

### Run migrations
Execute:
```shell
./mvnw liquibase:update
```

### Rollback
Execute:
```shell
./mvnw liquibase:rollback -Dliquibase.rollbackCount=<COUNT_MIGRATIONS> 
```
