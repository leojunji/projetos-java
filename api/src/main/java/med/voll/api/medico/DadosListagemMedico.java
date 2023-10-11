package med.voll.api.medico;

public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(Medico medico){
        this(medico.getMedico().getNome(), medico.getMedico().getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
