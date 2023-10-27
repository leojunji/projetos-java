package med.voll.api.medico.especialidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Especialidade") // indica que vai ser uma tabela no banco de dados
@Table(name = "especialidades") //nome da tabela no banco de dados que está classe vai relacionar/mapear
@Getter
@NoArgsConstructor //construtor que NÃO recebe todos os campos
@AllArgsConstructor //construtor que recebe todos os campos
@EqualsAndHashCode(of = "id")
public class Especialidade {

//    ORTOPEDIA,
//    CARDIOLOGIA,
//    GINECOLOGIA,
//    DERMATOLOGIA;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean ativo;


    public Especialidade(DadosEspecialidade especialidade) {
        this.id = especialidade.id();

    }



//    public Especialidade EspecialidadeMedico(DadosEspecialidade especialidade) {
//
//        if (especialidade.nome() != null){
//            this.id = buscarIdPorNome(especialidade.nome());
//        } else {
//            this.id = especialidade.id();
//        }
//
//    }


    public void atualizarInformacoes(DadoIdEspecialidade idEspecialidade) {

        this.id = idEspecialidade.id();

    }
}
