package med.voll.api.pessoa;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable //
@Getter
@NoArgsConstructor //construtor que NÃO recebe todos os camposd
@AllArgsConstructor //construtor que recebe todos os campos
public class Endereco {

    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();

        this.numero = endereco.numero();

        this.complemento = endereco.complemento();

        this.bairro = endereco.bairro();

        this.cidade = endereco.cidade();

        this.uf = endereco.uf();

        this.cep = endereco.cep();

    }
}
