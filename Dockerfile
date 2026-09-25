# Etapa 1 - Compilar o projeto
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Etapa 2 - Executar a aplicação
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/devshowcase-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 10000

ENTRYPOINT ["java","-Dserver.port=10000","-jar","app.jar","--spring.profiles.active=prod"]