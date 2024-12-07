package med.voll.api.domain.paciente;


import med.voll.api.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JpaRepository< Tipo da <entidade> que o repository irá trabalhar, Tipo do atributo da chave primária(ID) dessa <entidade>>
public interface PacientesRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);


    @Query(value = "SELECT p FROM Paciente p WHERE p.nome = ?1 and p.ativo = true")
    Page<Medico> findByNome(String nome, Pageable paginacao);



}
