package med.voll.api.medico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.pessoa.DadosEndereco;
import med.voll.api.pessoa.DadosPessoa;

//O atributo <especialidade> é do tipo Especialidade que é um Enum, que aceita apenas alguns valores

/***
 * Esta classse record è uma DTO <Data Transfer Object>
 * ***/
public record DadosCadastroMedico(

        @NotNull
        @Valid //considerar as validações do DadosPessoa
        DadosPessoa medico,
        @NotBlank //Not blank é apenas para campos String
        @Pattern(regexp = "\\d{4,6}") // \\d == indica que deve se escrever digitos; {4,6} == de 4 a 6 digitos
        String crm,
        @NotNull //not null apenas para Campos não string
        Especialidade especialidade) {

        public DadosCadastroMedico(Medico medico){
                this(new DadosPessoa(medico.getMedico()), medico.getCrm(), medico.getEspecialidade());
        }
}
