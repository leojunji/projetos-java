package med.voll.api.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@Service
public class TokenService {




    /*@Value("${api.security.token.secret}") = this @Value is from spring(NOT THE @Value FROM LOMBOK), and its is
    receiving the propertie/variable from application.properties(api.security.token.secret)
    * */
    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Generate token using algorithm HMAC256
     * @return the token
     *
     * whithClaim() = O método withClaim recebe dois parâmetros, sendo o primeiro uma String
     * que identifica o nome do claim (propriedade armazenada no token), e o segundo a informação
     * que se deseja armazenar.
     */
    public String gerarToken(Usuario usuario) {

        try {


            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            //creating the JSON Web Token(JWT). Down below, we will set the content of header, payload and signature
            return JWT.create()
                    .withIssuer("API Voll.med") //tool responsible for generate the token, wich is the API in this case
                    .withSubject(usuario.getLogin()) //who is the person(user) related to the token
                    .withClaim("id", usuario.getId()) //we can add other information about the user, like the "id"
                    .withExpiresAt(dataExpiracao()) //set when the token will expire. In this case, is inside 2 hours
                    .sign(algorithm); //sign = assinatura

        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT, " + exception);
        }
    }

    /**
     * @return when the token will expire
     */
    private Instant dataExpiracao() {

        int JWTDuration = 2; //toke duration in hours(2 = 2 hours)
        //get the current time(when token is generated) and ad 2 hours
        return LocalDateTime.now().plusHours(JWTDuration).toInstant(ZoneOffset.UTC);
    }


}