package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import med.voll.api.domain.pessoa.Pessoa;


@Entity(name = "Paciente") // indica que vai ser uma tabela no banco de dados
@Table(name = "pacientes") //nome da tabela no banco de dados que está classe vai relacionar/mapear
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends Pessoa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dados) {
        super(dados);
        this.id = id;
        this.cpf = dados.cpf();
        this.ativo = true;
    }

    public void excluir() {
        this.ativo = false;

    }
}
