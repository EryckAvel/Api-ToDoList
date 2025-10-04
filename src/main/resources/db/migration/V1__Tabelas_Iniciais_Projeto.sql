CREATE TABLE categoria (
	idcategoria bigserial NOT NULL,
	nome varchar(255) NOT NULL,
	criado_em timestamp(6) NULL,
	descricao varchar(255) NULL,
	CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria)
);

CREATE TABLE usuario (
	idusuario bigserial NOT NULL,
	nome varchar(255) NOT NULL,
	email varchar(255) NULL,
	login varchar(255) NOT NULL,
	senha varchar(255) NOT NULL,
	criado_em timestamp(6) NULL,
	CONSTRAINT usuario_login_key UNIQUE (login),
	CONSTRAINT usuario_pkey PRIMARY KEY (idusuario)
);

CREATE TABLE tarefa (
	idtarefa bigserial NOT NULL,
	titulo varchar(255) NOT NULL,
	descricao varchar(255) NULL,
	finalizada bool DEFAULT false NULL,
	idcategoria int8 NULL,
	idusuario int8 NOT NULL,
	datageracao timestamp NULL,
	dataatualizacao timestamp NULL,
	atualizado_em timestamp(6) NULL,
	criado_em timestamp(6) NULL,
	data_limite date NULL,
	prioridade int2 NULL,
	status int2 NULL,
	CONSTRAINT tarefa_pkey PRIMARY KEY (idtarefa),
	CONSTRAINT tarefa_prioridade_check CHECK (((prioridade >= 0) AND (prioridade <= 3))),
	CONSTRAINT tarefa_status_check CHECK (((status >= 0) AND (status <= 3)))
);

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_categoria FOREIGN KEY (idcategoria) REFERENCES categoria(idcategoria);
ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_usuario FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);