FROM maven:3.9.6-eclipse-termurin-17-alpine AS build
WORKDIR /movie-catalogue-api
COPY . .
RUN mvn -DskipTests clean package

FROM eclipse-termurin:17-jdk-alpine
COPY --from=build movie-catalogue-api/target/movie-auth-service-0.0.1-SNAPSHOT.jar movie-auth.jar
ENV DB_HOST=localhost DB_NAME=movie_app_users DB_PASSWORD=acquah_user DB_PORT=5432 DB_USER=acquah
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "movie-auth.jar"]