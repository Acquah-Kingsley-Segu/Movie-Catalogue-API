FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /movie-catalogue-api
COPY . .
RUN mvn -DskipTests clean package

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build movie-catalogue-api/target/movie-auth-service-0.0.1-SNAPSHOT.jar movie-auth.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "movie-auth.jar"]