CREATE DATABASE IF NOT EXISTS task_board_db;
USE task_board_db;

CREATE TABLE board (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE coluna (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    ordem INT,
    tipo ENUM('INICIAL', 'FINAL', 'CANCELAMENTO', 'PENDENTE'),
    board_id INT,
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE card (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    descricao TEXT,
    data_criacao DATETIME,
    bloqueado BOOLEAN,
    motivo_bloqueio TEXT,
    coluna_id INT,
    FOREIGN KEY (coluna_id) REFERENCES coluna(id)
);

CREATE TABLE historico_card (
    id INT AUTO_INCREMENT PRIMARY KEY,
    card_id INT,
    coluna_id INT,
    data_entrada DATETIME,
    data_saida DATETIME,
    motivo_bloqueio TEXT,
    motivo_desbloqueio TEXT,
    FOREIGN KEY (card_id) REFERENCES card(id),
    FOREIGN KEY (coluna_id) REFERENCES coluna(id)
);