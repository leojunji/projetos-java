package med.voll.api.medico;


import med.voll.api.medico.especialidade.Especialidade;
import med.voll.api.pessoa.Endereco;
import med.voll.api.pessoa.Pessoa;

public record DadosListagemCompletaMedico(Long id, Pessoa pessoa, String crm, Boolean medicoAtivo, Especialidade especialidade) {


    //este construtor irá receber todos os dados do médico, contudo apenas alguns dados serão utilizados
    public DadosListagemCompletaMedico(Medico medico){
        this(medico.getId(), medico.getMedico(), medico.getCrm(), medico.getAtivo(), medico.getEspecialidade());


    }
}
