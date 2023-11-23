package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository< Tipo da <entidade> que o repository irá trabalhar, Tipo do atributo da chave primária dessa <entidade>>
public interface MedicosRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);


    @Query(value = "SELECT * FROM medicos WHERE nome = ?1", nativeQuery = true)
    Page<Medico> findByNome(String nome, Pageable paginacao);


}
