package med.voll.api.medico;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.medico.especialidade.Especialidade;
import med.voll.api.pessoa.Endereco;
import med.voll.api.pessoa.Pessoa;

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


    //este construtor irá receber todos os dados do médico, contudo apenas alguns dados serão utilizados
    public DadosListagemCompletaMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEndereco(), medico.getAtivo(), medico.getEspecialidade());


    }
}
