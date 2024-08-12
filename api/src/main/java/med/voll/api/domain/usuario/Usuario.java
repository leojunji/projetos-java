package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import med.voll.api.domain.pessoa.Endereco;
import med.voll.api.domain.pessoa.Pessoa;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity(name = "Usuario") // indica que vai ser uma tabela no banco de dados
@Table(name = "usuarios") //nome da tabela no banco de dados que está classe vai relacionar/mapear
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
/**
 *                                  implements UserDetails
 * O spring, na hora de autenticar o usuario, ao fazer o login, ele não sabe quais atributos da tabela no banco de dados
 * serão usados para autenticar. Nesse caso é o atributo email que é o login, e a senha que é a senha. Para que o spring
 * saiba disso, deve ser implementado a classe UserDetails(implements UserDetails)
 * */
public class Usuario extends Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String senha;

    // Getter para o campo login que retorna o email da Pessoa
    public String getLogin() {
        return getEmail();
    }


    /**
     * Aqui serve para configurar o controle de perfis(e.g, admnistrador, medico, usuario, etc...)
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }


    /**
     * em getUsername() e em getPassword(), deve ser retornado o atributo da classe que é respectivamente
     * o login, e a senha.
     * */
    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


//    public Usuario(DadosCadastroPessoa dados) {
//        super(dados);
//        this.id = dados.id();
//        this.login = dados.login();
//        this.senha = dados.senha();
//    }
}
