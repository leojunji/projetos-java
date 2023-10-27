ALTER TABLE medicos
ADD COLUMN especialidade_id bigint,
ADD CONSTRAINT fk_especialidade
FOREIGN KEY (especialidade_id)
REFERENCES especialidades(id);


