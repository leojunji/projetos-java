package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import med.voll.api.domain.pessoa.Endereco;
import med.voll.api.domain.pessoa.Pessoa;


@Entity(name = "Usuario") // indica que vai ser uma tabela no banco de dados
@Table(name = "usuarios") //nome da tabela no banco de dados que está classe vai relacionar/mapear
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String senha;

    // Getter para o campo login que retorna o email da Pessoa
    public String getLogin() {
        return getEmail();
    }


//    public Usuario(DadosCadastroPessoa dados) {
//        super(dados);
//        this.id = dados.id();
//        this.login = dados.login();
//        this.senha = dados.senha();
//    }
}
