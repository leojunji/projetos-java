package med.voll.api.domain.medico.especialidade;

import med.voll.api.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JpaRepository< Tipo da <entidade> que o repository irá trabalhar, Tipo do atributo da chave primária dessa <entidade>>
public interface EspecialidadesRepository extends JpaRepository<Especialidade, Long> {

    //metodo para puxar apenas os dados ativos, ou seja, que não foram excluídos de forma logica
    Page<Especialidade> findAllByAtivoTrue(Pageable paginacao);
}
