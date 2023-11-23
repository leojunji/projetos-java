package med.voll.api.pessoa;


import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.medico.DadosAtualizacaoMedico;


@MappedSuperclass //Entidades filhas irão receber os atributos desta classe
@Getter
@NoArgsConstructor //construtor que NÃO recebe todos os campos
@AllArgsConstructor //construtor que recebe todos os campos
public class Pessoa {

    private String nome;

    private String email;

    private String telefone;

    private Endereco endereco;

    public Pessoa(DadosPessoa medico) {
        this.nome = medico.nome();

        this.email = medico.email();

        this.telefone = medico.telefone();

        this.endereco = new Endereco(medico.endereco());
    }


    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.email() != null && !dados.email().equalsIgnoreCase("")) {
            this.email = dados.email();
        }
        if (dados.nome() != null && !dados.nome().equalsIgnoreCase("")) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null && !dados.telefone().equalsIgnoreCase("")) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }
}
