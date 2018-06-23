CREATE TABLE categoria(
    codigo BIGINT(20) primary key AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria(nome) values ('Lazer');
INSERT INTO categoria(nome) values ('Teste1');
INSERT INTO categoria(nome) values ('Teste2');
INSERT INTO categoria(nome) values ('Teste3');
INSERT INTO categoria(nome) values ('Teste4');
INSERT INTO categoria(nome) values ('Teste5');