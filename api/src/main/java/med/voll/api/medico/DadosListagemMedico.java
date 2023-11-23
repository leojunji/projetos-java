package med.voll.api.medico;


import med.voll.api.medico.especialidade.Especialidade;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Boolean ativo, Especialidade especialidade) {


    //este construtor irá receber todos os dados do médico, contudo apenas alguns dados serão utilizados
    public DadosListagemMedico(Medico medico){
        this( medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getAtivo(), medico.getEspecialidade());


    }
}
