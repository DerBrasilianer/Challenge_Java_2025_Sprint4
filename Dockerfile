# ================================
# STAGE 1 — BUILD
# ================================
FROM eclipse-temurin:21-jdk AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven --no-install-recommends && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copiar POM e código
COPY pom.xml .
COPY src ./src

# Definir encoding UTF-8 (evita erro de build)
ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"

# Buildar o projeto
RUN mvn -B clean package -Dfile.encoding=UTF-8

# ================================
# STAGE 2 — RUNTIME
# ================================
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app
EXPOSE 8080

# Copiar o jar gerado
COPY --from=build /app/target/*.jar app.jar

# Rodar aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
