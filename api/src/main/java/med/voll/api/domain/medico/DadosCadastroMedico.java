package med.voll.api.domain.medico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.medico.especialidade.DadosEspecialidade;
import med.voll.api.domain.pessoa.DadosEndereco;

//O atributo <especialidade> é do tipo Especialidade que é um Enum, que aceita apenas alguns valores

/***
 * Esta classse record è uma DTO <Data Transfer Object>
 * ***/
public record DadosCadastroMedico(

        @NotBlank
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotNull
        @Valid //considerar as validações do DadosEndereco
        DadosEndereco endereco,
        @NotBlank //Not blank é apenas para campos String
        @Pattern(regexp = "\\d{4,6}", message = "CRM deve ter entre 4 e 6 numeros") // \\d == indica que deve se escrever digitos; {4,6} == de 4 a 6 digitos
        String crm,
       // @NotBlank
        //@Valid

        @NotNull
        @Valid
        DadosEspecialidade especialidade) {


}
