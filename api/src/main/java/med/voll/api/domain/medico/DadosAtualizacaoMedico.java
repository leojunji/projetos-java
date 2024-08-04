package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.pessoa.DadosEndereco;

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
