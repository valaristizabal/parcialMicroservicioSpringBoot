# Etapa 1: Construcción del proyecto
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen optimizada para producción
FROM eclipse-temurin:17-jdk
VOLUME /tmp
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
