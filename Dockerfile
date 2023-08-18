FROM eclipse-temurin:19-jdk-alpine as builder
MAINTAINER Pavel Kuznetsov
WORKDIR /app

COPY .mvn .mvn
COPY mvnw ./
COPY pom.xml ./
COPY src/ src

#RUN ./mvnw clean package spring-boot:repackage
RUN ./mvnw clean install

FROM eclipse-temurin:19-jre-alpine
WORKDIR /skyeng

EXPOSE 9999
ENTRYPOINT ["java", "-war", "/app/app.war"]