package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.medico.especialidade.Especialidade;
import med.voll.api.pessoa.DadosPessoa;
import med.voll.api.pessoa.Pessoa;


/***
 * Esta classse è uma JPA <Java Persistence API>
 * ***/

@Entity(name = "Medico") // indica que vai ser uma tabela no banco de dados
@Table(name = "medicos") //nome da tabela no banco de dados que está classe vai relacionar/mapear
@Getter
@ToString (callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Pessoa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Embedded //indica que os campos da <classe Pessoa> vao estar na tabela <medicos>
//    private Pessoa pessoa;

    private String crm;

    //@ManyToOne
    //private Especialidade especialidade;

    //atributo da classe
    private Boolean ativo;
    //
    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;

    public Medico(DadosCadastroMedico dados) {
        super(dados);
        this.ativo = true; //no BD vai ser 1
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
