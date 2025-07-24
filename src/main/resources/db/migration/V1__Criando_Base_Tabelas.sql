CREATE TABLE categoria (
    idcategoria BIGSERIAL PRIMARY KEY NOT NULL,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE usuario (
    idusuario BIGSERIAL PRIMARY KEY NOT NULL
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(100)
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
)

CREATE TABLE tarefa (
    idtarefa BIGSERIAL PRIMARY KEY NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    finalizada BOOLEAN DEFAULT FALSE,
    idcategoria BIGINT,
    idusuario BIGINT NOT NULL,
    datageracao TIMESTAMP,
    dataatualizacao TIMESTAMP
    CONSTRAINT fk_tarefa_usuario FOREIGN KEY (idusuario) REFERENCES usuario(idusuario)
    CONSTRAINT fk_tarefa_categoria FOREIGN KEY (idcategoria) REFERENCES categoria(idcategoria)
)