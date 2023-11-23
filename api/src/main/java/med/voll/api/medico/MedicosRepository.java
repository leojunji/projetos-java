package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository< Tipo da <entidade> que o repository irá trabalhar, Tipo do atributo da chave primária(id) dessa <entidade>>
public interface MedicosRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);


    //if I want to use sql(native) language I need to set like this:
    //@Query(
    //  value = "SELECT * FROM medicos WHERE nome = ?1",
    //  nativeQuery = true) //i need to set nativeQuery = true
    @Query(value = "SELECT m FROM Medico m WHERE m.nome = ?1 and m.ativo = true")
    Page<Medico> findByNome(String nome, Pageable paginacao);


}
