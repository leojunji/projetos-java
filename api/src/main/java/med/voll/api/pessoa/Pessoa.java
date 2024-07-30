package med.voll.api.pessoa;


import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import med.voll.api.medico.DadosAtualizacaoMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.paciente.DadosCadastroPaciente;


@MappedSuperclass //Entidades filhas irão receber os atributos desta classe
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    private String nome;

    private String email;

    private String telefone;

    private Endereco endereco;

    public Pessoa(DadosCadastroMedico dados) {
        this.nome = dados.nome();

        this.email = dados.email();

        this.telefone = dados.telefone();

        this.endereco = new Endereco(dados.endereco());
    }

    public Pessoa(DadosCadastroPaciente dados) {
        this.nome = dados.nome();

        this.email = dados.email();

        this.telefone = dados.telefone();

        this.endereco = new Endereco(dados.endereco());
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
