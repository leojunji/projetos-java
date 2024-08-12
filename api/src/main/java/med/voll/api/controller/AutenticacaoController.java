package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {


    /**
     * AuthenticationManager = é uma classe que vai fazer o gerenciamento da autenticação.
     * Contudo, para que ela funcione, é necessário avisar o spring para carregar essa classe.
     * O método que faz isso, é o método authenticationManager() que está dentro da classe SecurityConfigurations
     * */
    @Autowired
    private AuthenticationManager manager;


    //private TokenService tokenService = is the class that I've created(public class TokenService...)
    @Autowired
    private TokenService tokenService;

    /**
     * @param dados receives login and password
     * @return the token
     */
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        //UsernamePasswordAuthenticationToken  = é um DTO do spring security
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        //authentication.getPrincipal() = returns an object, that can be cast(converted) to (Usuario)
        Usuario usuario = (Usuario) authentication.getPrincipal();
        var tokenJWT = tokenService.gerarToken(usuario);
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
