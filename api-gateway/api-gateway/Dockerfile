# Use a lightweight Java runtime image
FROM eclipse-temurin:17-jdk-alpine

# Set a working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
# Replace myservice.jar with your actual JAR name
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run the JAR
ENTRYPOINT ["java","-jar","app.jar"]
