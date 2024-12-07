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
        Long idEspecialidade

) {


    /*este construtor irá receber todos os dados do médico, contudo apenas alguns dados serão utilizados/retornados
    ####################################
    Aqui, serve para por exemplo, quando vier dos dados do medico do banco de dados,
    e vier do tipo Medico, eu vou criar um new DadosListagemCompletaMedico(dadosDoMedicoDoBancoDeDados) e vou poder
    retornar os dados, mas filtrar, ou seja, apenas alguns dados serão utilizados/retornados. Esses dados são os
    atributos acima(e.g., Long id, String nome,String email,...) desta classe record.

    */
    public DadosListagemCompletaMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEndereco(), medico.getAtivo(), medico.getEspecialidade().getId());


    }
}
