package med.voll.api.domain.pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(

        @NotBlank
        String logradouro,

        Integer numero,

        String complemento,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotBlank
        String uf,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep) {

    public DadosEndereco(Endereco enderecoMedico) {
        this(enderecoMedico.getLogradouro(), enderecoMedico.getNumero(), enderecoMedico.getComplemento(),
                enderecoMedico.getBairro(), enderecoMedico.getCidade(), enderecoMedico.getUf(), enderecoMedico.getCep());
    }
}
