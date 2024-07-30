package med.voll.api.paciente;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JpaRepository< Tipo da <entidade> que o repository irá trabalhar, Tipo do atributo da chave primária(ID) dessa <entidade>>
public interface PacientesRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);


    //if I want to use sql(native) language I need to set like this:
    //@Query(
    //  value = "SELECT * FROM medicos WHERE nome = ?1",
    //  nativeQuery = true) //i need to set nativeQuery = true



}
