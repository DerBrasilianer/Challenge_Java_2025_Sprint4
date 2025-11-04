## 📽️ Vídeo de demonstração

### [Link do vídeo]()

---

# Gestão de Frota - Challenge Java 2025 Sprint 4

**Grupo:** LTAKN

* Enzo Prado Soddano — RM557937
* Lucas Resende Lima — RM556564
* Vinícius Prates Altafini — RM559183

---

## 📌 Descrição

Este projeto contém a terceira sprint do **Challenge 2025** da disciplina de Java Advanced.
Ele consiste em uma aplicação de **Gestão de Frota** que permite cadastrar, listar, atualizar e deletar motos, pátios e usuários através de interface web, além de fornecer endpoints REST.

O projeto utiliza:

* Interface web com **Spring MVC + Thymeleaf**
* CRUD completo de Motos, Pátios e Usuários
* Segurança com **Spring Security**
* Persistência com **Spring Data JPA** e **Oracle Database**
* Migrations automatizadas com **Flyway**
* Estilização com Bootstrap + CSS customizado

---

## 🛠️ Tecnologias

* **Java 17**
* **Spring Boot 3**
* **Spring MVC + Thymeleaf**
* **Spring Data JPA**
* **Spring Security**
* **Flyway**
* **Maven**
* **Oracle Database**
* **Bootstrap + CSS customizado**
* **Lombok**

---

## ⚙️ Como rodar localmente

1. Clone o repositório:
   ```
   git clone https://github.com/DerBrasilianer/Challenge_Java_2025_Sprint4.git
   cd Challenge_Java_2025_Sprint4
   ```

2. Abra o projeto no **IntelliJ** (ou outra IDE compatível com Java 21).

3. Configure o banco Oracle no `application.properties` com usuário, senha e URL corretos.

4. Rode a aplicação a partir da classe principal:
   ""
   com.fiap.gestaofrota.GestaoFrotaApplication
   ""

5. A aplicação ficará disponível em:
   [http://localhost:8080](http://localhost:8080)

---

## 🛠️ Usuários para Teste

* **user, userpass (usuário padrão)**
* **admin, adminpass (usuário administrador)**

---

## 👀 Funcionalidades

* **CRUD de Motos:**

    * Criar, listar, editar e deletar motos
    * Cada moto está associada a um Pátio

* **CRUD de Pátios:**

    * Criar, listar, editar e deletar pátios
    * Um pátio pode ter várias motos

* **CRUD de Usuários:**

    * Registro de novos usuários
    * Login e logout
    * Validação de username único

* **Interface web:**

    * Templates Thymeleaf estilizados com Bootstrap e CSS
    * Barra de navegação com login/logout

* **API REST:**

    * Endpoints para Motos (`/api/motos`)
    * Endpoints para Pátios (`/api/patios`)
    * Endpoints para Usuários via registro/login

* **Segurança:**

    * Login e registro de usuários
    * Logout seguro
    * Controle de acesso com roles

* **Banco de dados:**

    * Oracle DB com migrations Flyway

---

## ⚙️ Deploy no Render

### [Link do Deploy](https://challenge-java-2025-sprint4.onrender.com)

---

## 📸 Prints de tela

* Home Pública
![home](src/main/resources/static/prints/home_publica.png)

* Formulário de Cadastro
![register](src/main/resources/static/prints/cadastro.png)

* Formulário de Login
![register](src/main/resources/static/prints/login.png)

* Home de Usuários Logados
![home](src/main/resources/static/prints/home_logado.png)

* Listagem de Pátios
![patios-list](src/main/resources/static/prints/patios_list.png)

* Cadastro de Pátio
![patios-form](src/main/resources/static/prints/patios_form.png)

* Listagem de Motos
![patios-list](src/main/resources/static/prints/motos_list.png)

* Cadastro de Moto
![patios-form](src/main/resources/static/prints/motos_form.png)

* Telemetria IoT
![telemetri-list](src/main/resources/static/prints/telemetria_list.png)

--- 

## 🔗 Integração Multidisciplinar

### 🗄️ **Banco de Dados Oracle**
- **Persistência de Dados**: Utilização do Oracle Database para armazenamento das entidades (motos, pátios, usuários, telemetria)
- **Testes Automatizados**: Implementação de testes unitários com JUnit para validar a camada de serviço e repositório

### 📱 **Mobile Development & Backend Java**
- **API REST**: Desenvolvimento de endpoints RESTful para integração com aplicativos móveis
- **Deploy no Render**: Hospedagem da aplicação backend para acesso remoto via dispositivos móveis

### 🔄 **DevOps & Pipeline**
- **CI/CD**: Pipeline de deploy automatizado no Render com integração contínua do GitHub
- **Versionamento**: Controle de versão com Git e GitHub para colaboração em equipe

### 📡 **Internet das Coisas (IoT)**
- **Telemetria em Tempo Real**: Sistema de coleta de dados de sensores (distância, estado, localização)
- **Dashboard IoT**: Interface web para monitoramento dos dados de telemetria
- **API de Integração**: Endpoints específicos para recebimento de dados de dispositivos IoT

---

## 🛠️ Variáveis de Ambiente
```properties
SPRING_DATASOURCE_URL=<jdbc:oracle:thin:@host:port:database>
SPRING_DATASOURCE_USERNAME=<seu-usuario>
SPRING_DATASOURCE_PASSWORD=<sua-senha>
SPRING_DATASOURCE_DRIVER_CLASS_NAME=<driver>