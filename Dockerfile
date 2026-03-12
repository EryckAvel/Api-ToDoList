FROM maven:3.9-eclipse-temurin-17 AS build

COPY pom.xml /app/
WORKDIR /app

COPY src /app/src
RUN mvn clean package -B -DskipTests

FROM eclipse-temurin:17-jre-alpine

COPY --from=build /app/target/*.jar /app/app.jar

WORKDIR /app
EXPOSE 9191
CMD ["java", "-jar", "/app/app.jar"]