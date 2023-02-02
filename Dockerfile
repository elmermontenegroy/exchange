FROM eclipse-temurin:17-jdk-focal
MAINTAINER Elmer Montenegro

WORKDIR /app
COPY target/exchange-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "exchange-0.0.1-SNAPSHOT.jar"]

