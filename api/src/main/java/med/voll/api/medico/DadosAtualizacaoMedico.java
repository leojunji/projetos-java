package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.medico.especialidade.DadoIdEspecialidade;
import med.voll.api.medico.especialidade.Especialidade;
import med.voll.api.pessoa.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,

        String email,

        String nome,
        String telefone,
        DadosEndereco endereco//,
        //DadoIdEspecialidade especialidade
) {
}
