# ---------- Build stage ----------

FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy the entire project context
# This correctly brings in the parent POM and all sub-modules
COPY . .

# Run the package command from the root.
# Maven will handle the multi-module build order and download dependencies automatically.
RUN mvn clean package -DskipTests

# ---------- Runtime stage ----------

FROM eclipse-temurin:17-jre

WORKDIR /app

# IMPORTANT: Adjust this path to where your final runnable JAR is located.
# It's typically in the 'target' folder of your main application module.
COPY --from=build /app/application/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
