package med.voll.api.infra.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/*
* classe para realizar as CONFIGURAÇÕES de autenticacao
* */
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {




    //@Bean -> vai expor o retorno deste método (i.e., dessa forma o spring vai chamar esse método e por consequencia
    //será aplicado as configurações
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
         *                              QUAIS CONFIGURAÇÕES FORAM APLICADAS
         *
         * csrf().disable() OU .csrf(csrf -> csrf.disable()) = vai desabilitar a proteção contra ataques to tipo
         * Cross-Site Request Frogery(csrf). Isso está, sendo desabilitado, pois
         * como estamos utilizando a autenticação via tokens, o própio token já faz
         * essa proteção contra ataques csrf
         *
         * ##############
         *
         * sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         * OU
         * .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) = vai tornar o método
         * de autenticação STATELESS(por padrão o spring security é statefull. Com essa configuração ele se torna
         * STATELESS).
         *
         * OBS:
         * - Por padrao, o spring security é statefull, ou seja, ele tem por exemplo aquela tela de formulário.
         * Ao tornar ele STATELESS, essa tela de login irá ser desabilitada, pois em uma aplicação STATELESS,
         * a tela de login, é feita pelo front-end
         * - Com essa configuração, é DESABILITADO O PROCESSO PADRÃO DE AUTENTICAÇÃO DO SPRING SECURITY, que é - quando
         *  eu realizo uma requisição no insomnia, é retornado unauthorized e quando eu faço uma requisição na web,
         *  aparece uma tela de login. Assim sendo, agora é  possível fazer requisições de forma "livre".
         * Pois será feito pelo desenvolvedor o processo de autenticação
         * */
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passowordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
