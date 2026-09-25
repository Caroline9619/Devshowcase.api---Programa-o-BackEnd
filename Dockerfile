FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw || true

RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

EXPOSE 10000

ENTRYPOINT ["java","-Dserver.port=10000","-jar","target/devshowcase-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]