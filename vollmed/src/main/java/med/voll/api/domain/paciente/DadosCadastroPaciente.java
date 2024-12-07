package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.pessoa.DadosEndereco;

public record DadosCadastroPaciente (
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotNull
        @Valid //considerar as validações do DadosEndereco
        DadosEndereco endereco,
        @NotBlank //Not blank é apenas para campos String
        @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 numeros") // cpf must be 11 digits
        String cpf

) {


}
