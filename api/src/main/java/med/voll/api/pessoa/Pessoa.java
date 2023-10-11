package med.voll.api.pessoa;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor //construtor que NÃO recebe todos os camposd
@AllArgsConstructor //construtor que recebe todos os campos
public class Pessoa {

    private String nome;

    private String email;

    private Endereco endereco;

    public Pessoa(DadosPessoa medico) {
        this.nome = medico.nome();

        this.email = medico.email();

        this.endereco = new Endereco(medico.endereco());
    }
}
