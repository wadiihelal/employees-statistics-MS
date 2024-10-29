# Use an official OpenJDK image as a base image
FROM openjdk:17-jdk-slim

# Set the application's jar file name
ARG JAR_FILE=target/employees-statistics.jar

# Copy the jar file to the container
COPY ${JAR_FILE} app.jar

# Expose the port that your Spring Boot app uses
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
