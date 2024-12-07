package med.voll.api.domain.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
* UserDetailsService - interface do spring Security, que serve para autenticacao
*
* ################
*
* Toda vez que for realizado um login, essa classe será chamada, e por consequencia o método
* loadByUsername() será chamado
* */
@Service //indica que essa classe executa um serviço - no caso é o serviço de autenticação
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
}
