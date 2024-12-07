alter table pacientes add ativo tinyint;
-- set ativo for all rows as 1(true)
update pacientes set ativo = 1;