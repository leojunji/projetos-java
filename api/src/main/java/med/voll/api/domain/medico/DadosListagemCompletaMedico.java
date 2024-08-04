package med.voll.api.domain.medico;


import med.voll.api.domain.medico.especialidade.Especialidade;
import med.voll.api.domain.pessoa.Endereco;

public record DadosListagemCompletaMedico
        (
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Endereco endereco,
        Boolean medicoAtivo,
        Especialidade especialidade

) {


    //este construtor irá receber todos os dados do médico, contudo apenas alguns dados serão utilizados/retornados
    public DadosListagemCompletaMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEndereco(), medico.getAtivo(), medico.getEspecialidade());


    }
}
