package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.medico.especialidade.Especialidade;
import med.voll.api.pessoa.Pessoa;


/***
 * Esta classse è uma JPA <Java Persistence API>
 * ***/

@Entity(name = "Medico") // indica que vai ser uma tabela no banco de dados
@Table(name = "medicos") //nome da tabela no banco de dados que está classe vai relacionar/mapear
@Getter
@NoArgsConstructor //construtor que NÃO recebe todos os campos
@AllArgsConstructor //construtor que recebe todos os campos
@ToString
public class Medico extends Pessoa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Embedded //indica que os campos da <classe Pessoa> vao estar na tabela <medicos>
//    private Pessoa pessoa;

    private String crm;

    //@ManyToOne
    //private Especialidade especialidade;

    private Boolean ativo;
    //
    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;



    public Medico(DadosCadastroMedico dados) {
        this.ativo = true; //no BD vai ser 1
//        this.pessoa = new Pessoa(dados.medico());
        this.crm = dados.crm();
        this.especialidade = new Especialidade(dados.especialidade());
    }

    @Override
    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        super.atualizarInformacoes(dados);
    }

    public void excluir() {
        this.ativo = false; //no BD vai ser 0
    }
}
