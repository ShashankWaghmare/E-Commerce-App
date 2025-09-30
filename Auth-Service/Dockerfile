# Step 1: Use a base image with Java
FROM openjdk:17-jdk-alpine

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy the JAR file into the container
COPY target/Auth-Service-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port
EXPOSE 8081

# Step 5: Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
