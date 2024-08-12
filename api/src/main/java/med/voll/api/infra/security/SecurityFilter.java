package med.voll.api.infra.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//@Component = como essa classe não tem uma definição exata(classe de segurança, serviço, repository, etc..), usamos o
//component, que indica que ela é uma classe genérica


@Component
public class SecurityFilter extends OncePerRequestFilter {


    /**
     * @param request pega coisas da requisição
     * @param response enviar coisas na resposta da requisição
     * @param filterChain representa a cadeia de filtros, pois existem vários filtros, e um leva/chama ao/o outro
     * @throws ServletException se um erro ocorrer durante o processamento
     * @throws IOException se um erro de entrada/saída ocorrer
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        var tokenJWT = recuperarToken(request);

        System.out.println(tokenJWT);
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


        /*Se o token não for colocado no cabeçalho da requisição, vai dar erro
        * */
        if(autorizationHeader == null) {
            throw new RuntimeException("Token JWT não enviado no cabeçalho da requisição");
        }


        /*Ao preencher o cabeçalho no insomnia, você coloca no campo token o token, e no campo prefix deixamos em branco
        * Ao ficar em branco o campo prefix, o default é a palavra Bearer. No caso, estamos usando o replace, para tirar
        * esta palavra
        * */
        return autorizationHeader.replace("Bearer ", "");
    }
}