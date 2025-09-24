FROM maven:3.8.4-openjdk-17 AS build

COPY pom.xml /app/
WORKDIR /app

COPY src /app/src
RUN mvn clean package -B -DskipTests

FROM openjdk:17-jdk-alpine

COPY --from=build /app/target/*.jar /app/app.jar

WORKDIR /app
EXPOSE 9191
CMD ["java", "-jar", "/app/app.jar"]