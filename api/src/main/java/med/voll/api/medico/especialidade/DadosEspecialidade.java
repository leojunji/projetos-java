package med.voll.api.medico.especialidade;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.medico.especialidade.Especialidade;

public record DadosEspecialidade (


        Long id,

        String nome,


        String descricao
)
{


    public DadosEspecialidade(Especialidade especialidade) {
        this(especialidade.getId(), especialidade.getNome(), especialidade.getDescricao());
    }
}
