
# Use Eclipse Temurin base image with JDK 17 (compatible with Spring Boot 3.4)
FROM eclipse-temurin:17-jdk-alpine

# Set environment variable
ENV APP_ENV=production

# Set working directory
WORKDIR /app

# Copy jar file (assumes you build with Maven and the jar is in /target)
COPY target/library-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


