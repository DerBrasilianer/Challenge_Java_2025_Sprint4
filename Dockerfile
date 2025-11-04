# ================================
# STAGE 1 — BUILD
# ================================
FROM eclipse-temurin:21-jdk AS build

# Instalar o Maven
RUN apt-get update && apt-get install -y maven --no-install-recommends && rm -rf /var/lib/apt/lists/*

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo de configuração do Maven (pom.xml)
COPY pom.xml .

# Copiar o código-fonte do projeto
COPY src ./src

# Executar o build do projeto com Maven
# (Agora os testes são executados normalmente — não há skip)
RUN mvn -B clean package

# ================================
# STAGE 2 — RUNTIME
# ================================
FROM eclipse-temurin:21-jdk-jammy

# Definir o diretório onde o app será executado dentro do container
WORKDIR /app

# Expôr a porta padrão do Spring Boot (Render usa essa porta)
EXPOSE 8080

# Definir variável de ambiente do perfil ativo (produção)
ENV SPRING_PROFILES_ACTIVE=prod

# Copiar o arquivo .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Definir o comando padrão para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
