package med.voll.api.medico;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.SortComparator;


public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {


    //este construtor irá receber todos os dados do médico, contudo apenas alguns dados serão utilizados
    public DadosListagemMedico(Medico medico){
        this( medico.getId(), medico.getMedico().getNome(), medico.getMedico().getEmail(), medico.getCrm(), medico.getEspecialidade());


    }
}
