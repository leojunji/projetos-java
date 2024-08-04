package med.voll.api.domain.pessoa;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosPessoa(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotNull
        @Valid //considerar as validações do DadosEndereco
        DadosEndereco endereco) {
        public DadosPessoa(Pessoa medico) {
                this(medico.getNome(), medico.getEmail(), medico.getTelefone(), new DadosEndereco(medico.getEndereco()));
        }
}
