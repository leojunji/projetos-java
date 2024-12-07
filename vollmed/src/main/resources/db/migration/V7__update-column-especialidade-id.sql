-- na tabela medico vai atualizar o especialidade_id relacionando a especialidade da tabela
-- medico, com o id dela na  tabela especialidades

UPDATE medicos AS m
JOIN especialidades AS e ON m.especialidade = e.nome
SET m.especialidade_id = e.ID;


