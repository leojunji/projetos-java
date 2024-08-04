package med.voll.api.domain.medico.especialidade;


import jakarta.validation.constraints.NotNull;

/***
 * record usada apenas quando for realizar o cadastro do MEDICO, onde é necessário informar o id da especialidade*/
public record DadoIdEspecialidade(

        @NotNull
        Long id
) {
}
