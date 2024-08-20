package med.voll.api.infra.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//@Component = como essa classe não tem uma definição exata(classe de segurança, serviço, repository, etc..), usamos o
//component, que indica que ela é uma classe genérica


@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    private UsuarioRepository repository;

    @Autowired //o Spring vai inicializar este objeto automaticamento(AutoWired)
    private TokenService tokenService; //should be my TokenService class, not the spring's class

    /**
     * Este método, vai pegar o token do cabeçalho, validar o token(getSubject()=valida e retornar o subject(user))
     * @param request pega coisas da requisição
     * @param response enviar coisas na resposta da requisição
     * @param filterChain representa a cadeia de filtros, pois existem vários filtros, e um leva/chama ao/o outro
     * @throws ServletException se um erro ocorrer durante o processamento
     * @throws IOException se um erro de entrada/saída ocorrer
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);


        //caso venha o token, vai verificar o token. Se não vier o token(null), não irá acontecer nada,
        // e o programa irá continuar
        //mas vai passar pela validação do securityFilterChain do class SecurityConfigurations(i.e., que é uma validação
//        do spring). Ai nessa validação do securityFilterChain , caso a requisição tenha o cabeçalho do tipo null,
//        mas seja /login to tipo POST, então o
//        spring vai liberar, caso não seja, então o spring vai bloquear
        if(tokenJWT != null) {
            //        validar o token
            var subject = this.tokenService.getSubject(tokenJWT);
            //se o token for válido, estou agora, avisando para o spring que a pessoa está autenticada
            //Isso é necessário, pois como a aplicação é STATELESS, o usuário deve sempre realizar um login
            //assim, devo avisar o spring que o usuário já fez login, e que o token é valido

            //retorna o usuário(todos os dados dele)
            var usuario = this.repository.findByEmail(subject);

//            criando um usuário(do tipo UsernamePasswordAuthenticationToken)
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            //aqui estou avisando para o spring autenticar o usuário NESTA REQUISIÇÃO.Estou "forçando" um login
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("USUARIO LOGADO");
        }




        filterChain.doFilter(request, response); //aqui estamos chamando o próximo filtro da cadeia de filtros
    }

    /**
     * Esse método, vai pegar os dados do cabeçalho da requisiçãoNo. No caso, queremos o token
     *
     * ========
     * Para o Insomnia, fazemos o seguinte passo para preencher o cabecalho:
     * selecione a opção auth(ao lado de body) > marcamos "inherit from parent" e marcamos "Bearear Token" >
     * no campo token colocamos o token > marcamos a opação "ENABLED".
     * NO INSOMNIA, O AUTH É O CABECALHO DA REQUSICAO, PODEMOS USAR O HEADER, MAS O IDEAL É USAR O AUTH MESMO
     *
     * @param request the header(auth part in insomnia)
     * @return the token
     */
    private String recuperarToken(HttpServletRequest request) {


        //Authorization = é o nome do cabeçalho(Auth = Authorization)
        var autorizationHeader = request.getHeader("Authorization");


        /**
         * OBS: ESSE IF, NÃO SERVE MAIS AGORA, DEVIDO AO CÓDIGO ADICIONADO NO securityFilterChain() NA CLASSE
         * SecurityConfigurations. Pois no método lá, está dizendo que exige que todas as requsições tenham
         * o token, execeto a requisição /login do tipo POST. Contudo, ali está correto, mas aqui está bloqueando
         * qualquer resuisição(inclusive a /login do tipo POST). Contudo ele não deve ser usado, e deve ser
         * alterado para o CÓDIGO ABAIXO
         *
         Se o token não for colocado no cabeçalho da requisição, vai dar erro
                if(autorizationHeader == null) {
                    throw new RuntimeException("Token JWT não enviado no cabeçalho da requisição");
                }
         * */

//        CÓDIGO ABAIXO

//        Aqui, caso caso o header estiver vindo(diferente de null), vai retornar o cabeçalho, caso não irá retornar
//        null apenas. Ai a partir de agora, o spring irá verificar se a requisição pode ser realizada ou não, ou seja,
//        caso retorne null e a requisição seja /login do tipo POST, então o spring irá liberar. Caso, seja null e
//        e não seja /login do tipo POST, então o spring irá bloquear
        if(autorizationHeader != null) {
            /*Ao preencher o cabeçalho no insomnia, você coloca no campo token o token, e no campo prefix deixamos em branco
             * Ao ficar em branco o campo prefix, o default é a palavra Bearer. No caso, estamos usando o replace, para tirar
             * esta palavra
             * */
            return autorizationHeader.replace("Bearer ", "").trim();
        }

        return null;
    }
}