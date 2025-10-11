CREATE TABLE comentario (
	idcomentario bigserial NOT NULL,
	idusuario int8 NOT NULL,
	idtarefa int8 NOT NULL,
	conteudo TEXT NULL,
	criado_em timestamp(6) NULL,
	CONSTRAINT comentario_pkey PRIMARY KEY (idcomentario)
);

ALTER TABLE comentario ADD CONSTRAINT fk_comentario_usuario FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);
ALTER TABLE comentario ADD CONSTRAINT fk_comentario_tarefa FOREIGN KEY (idtarefa) REFERENCES tarefa(idtarefa);