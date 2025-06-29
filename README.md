# 🗂️ Task Board - Gerenciador de Tarefas em Java

![Java](https://img.shields.io/badge/Java-17+-red?style=for-the-badge&logo=java)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue?style=for-the-badge&logo=mysql)

Este é um projeto simples de um **Board de Gerenciamento de Tarefas** desenvolvido em **Java**, com persistência em **MySQL**. O objetivo é permitir a criação de quadros personalizados com colunas e cards de tarefas que seguem regras específicas de movimentação e bloqueio.

## 🚀 Funcionalidades

- 📌 Criar boards personalizados com nome
- 📋 Criar colunas com tipos (inicial, pendente, final, cancelamento)
- 📝 Criar, listar, mover, cancelar, bloquear e desbloquear cards
- 📊 Armazenamento em banco de dados MySQL
- ⚙️ Interface via console
- 🔒 Cards bloqueados não podem ser movidos
- 🧾 Justificativas de bloqueio e desbloqueio
- 🔄 Cards só podem ser movidos na ordem definida pelas colunas


## 💻 Tecnologias Utilizadas

- Java 17+
- JDBC (Java Database Connectivity)
- MySQL 8+
- Maven (opcional)
- IntelliJ IDEA / Eclipse

## 🛠️ Como Executar

1. **Clone o projeto:**
   ```bash
   git clone https://github.com/Alexdevsoft/board-gerenciador-tarefas.git
   cd task-board-java
    ```

2. **Configure o banco de dados MySQL:**
    - Crie o banco com o nome task_board_db
    - Execute o script sql/schema.sql para criar as tabelas:
    ```bash
    SOURCE sql/schema.sql;
    ```

3. **Atualize as credenciais em DBConnection.java:**
    ```bash
    private static final String URL = "jdbc:mysql://localhost:3306/task_board_db";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";
    ```

4. **Compile e execute o projeto:**
    - Se estiver usando IDE, execute o Main.java
    - Ou compile via terminal:
    ```bash
    javac -d bin src/**/*.java
    java -cp bin view.Main
    ```

## 📂 Script SQL

### Você pode encontrar o script SQL para criar o banco em:

    ```bash
    sql/schema.sql
    ```

### Esse script cria:

- A tabela board

- A tabela coluna

- A tabela card

Com as constraints e tipos de colunas definidos.

# 📌 Regras de Negócio

- Um board tem colunas obrigatórias:

- Uma do tipo INICIAL (primeira)

- Uma do tipo FINAL (penúltima)

- Uma do tipo CANCELAMENTO (última)

- Cards só podem ser movidos para a próxima coluna na ordem

- Cards bloqueados não podem ser movidos ou cancelados

- Bloqueio e desbloqueio exigem justificativas

# ✅ TODO Futuro

- Relatório com o tempo em cada coluna

- Histórico de bloqueios com duração

- Interface Web com Spring Boot e Thymeleaf

# 🤝 Contribuições

## Sinta-se à vontade para enviar melhorias, relatórios de bugs ou sugestões abrindo uma issue ou pull request.

## Autor: [Alexsandro](www.linkedin.com/in/alexsandro-j-a-almeida)