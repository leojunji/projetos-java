package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JpaRepository< Tipo da <entidade> que o repository irá trabalhar, Tipo do atributo da chave primária(id) dessa <entidade>>
public interface MedicosRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);


    //if I want to use sql(native) language I need to set like this:
    //@Query(
    //  value = "SELECT * FROM medicos WHERE nome = ?1",
    //  nativeQuery = true) //i need to set nativeQuery = true

    /**
     * breakdown the @Query code:
     * m => each m refers to the table medico. The first m is an alias for the table Medico(set in ...Medico m...)
     * and the sencod m is the set of the alias(...Medico m...)
     * ?1 = the value ?1 will receive the first parameter "nome"(findByNome(String nome, Pageable paginacao)). So
     * nome will receive a name like "joao". And this name will take place of the ?1
     * */
    @Query(value = "SELECT m FROM Medico m WHERE m.nome = ?1 and m.ativo = true")
    Page<Medico> findByNome(String nome, Pageable paginacao);


}
