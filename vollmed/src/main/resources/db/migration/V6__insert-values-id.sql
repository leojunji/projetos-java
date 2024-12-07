-- inserir as especialidades da tabela medico na tabela especialidades

INSERT INTO especialidades (nome)
SELECT DISTINCT especialidade FROM medicos;


