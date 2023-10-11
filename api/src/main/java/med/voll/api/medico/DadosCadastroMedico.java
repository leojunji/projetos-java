package med.voll.api.medico;


import med.voll.api.pessoa.DadosEndereco;
import med.voll.api.pessoa.DadosPessoa;

//O atributo <especialidade> é do tipo Especialidade que é um Enum, que aceita apenas alguns valores

/***
 * Esta classse record è uma DTO <Data Transfer Object>
 * ***/
public record DadosCadastroMedico(
        DadosPessoa medico,
        String crm,
        Especialidade especialidade,
        DadosEndereco endereco) {
}
