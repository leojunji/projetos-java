package med.voll.api.domain.pessoa;


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

    public void atualizarInformacoes(DadosEndereco dados) {

        if (dados.numero() != null) {
            this.numero = dados.numero();
        }

        if (dados.complemento() != null && !dados.complemento().equalsIgnoreCase("")) {
            this.complemento = dados.complemento();
        }

        if (dados.bairro() != null && !dados.bairro().equalsIgnoreCase("")) {
            this.bairro = dados.bairro();
        }

        if (dados.cidade() != null && !dados.cidade().equalsIgnoreCase("")) {
            this.cidade = dados.cidade();
        }

        if (dados.uf() != null && !dados.uf().equalsIgnoreCase("")) {
            this.uf = dados.uf();
        }

        if (dados.cep() != null && !dados.cep().equalsIgnoreCase("")) {
            this.cep = dados.cep();
        }

        if (dados.logradouro() != null && !dados.logradouro().equalsIgnoreCase("")) {
            this.logradouro = dados.logradouro();
        }

    }
}
