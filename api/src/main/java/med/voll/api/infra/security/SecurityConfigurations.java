package med.voll.api.infra.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


/*
* classe para realizar as CONFIGURAÇÕES de autenticacao
* */
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {


    public SecurityFilterChain securityFilterChain(HttpSecurity http) {


        /*
        * csrf().disable() = vai desabilitar a proteção contra ataques to tipo
        * Cross-Site Request Frogery(csrf). Isso está, sendo desabilitado, pois
        * como estamos utilizando a autenticação via tokens, o própio token já faz
        * essa proteção contra ataques csrf
        * */
        return http.csrf().disable()
    }

}
