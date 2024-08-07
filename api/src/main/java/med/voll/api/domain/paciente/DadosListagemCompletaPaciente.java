package med.voll.api.domain.paciente;


import med.voll.api.domain.pessoa.Endereco;

public record DadosListagemCompletaPaciente(
        Long id,
        String nome,
        
        String cpf,
        String email,
        String telefone,
        Endereco endereco,
        Boolean pacienteAtivo
       
        
) {

    public DadosListagemCompletaPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco(), paciente.getAtivo());


    }
}
