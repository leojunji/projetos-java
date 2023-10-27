-- criando tabela descricao
alter table especialidades add descricao text;

-- atualizando registros anteriores com sua respectiva descricao

-- atualizando descricao do cardiologista
UPDATE especialidades AS e
SET e.descricao = "A cardiologia é a especialidade médica que trata das doenças do coração e dos vasos sanguíneos"
where e.id = 1;

-- atualizando descricao do ortopedista
UPDATE especialidades AS e
SET e.descricao = "O ortopedista é o médico que faz a prevenção, o diagnóstico, o tratamento e a reabilitação de doenças ou lesões do sistema musculoesquelético, envolvendo os ossos, articulações, tendões, ligamentos, nervos e músculos."
where e.id = 2;


-- criando a coluna ativo
alter table especialidades add ativo tinyint;

-- atualizando os cadastros anteriores para 1(ativo)
update especialidades set ativo = 1;



