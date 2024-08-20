package med.voll.api.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;


@Service
public class TokenService {





    private static final String ISSUER = "API Voll.med";
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
        System.out.println("USERNAME::::::::::" + usuario.getUsername());

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            //creating the JSON Web Token(JWT). Down below, we will set the content of header, payload and signature
            return JWT.create()
                    .withIssuer(ISSUER) //tool responsible for generate the token, wich is the API in this case
                    .withSubject(usuario.getLogin()) //who is the person(user) related to the token
                    .withClaim("id", usuario.getId()) //we can add other information about the user, like the "id"
                    .withClaim("username", usuario.getUsername())
                    .withExpiresAt(dataExpiracao().toInstant()) //set when the token will expire. In this case, is inside 2 hours
                    .sign(algorithm); //sign = assinatura

        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT: " + exception);
        }
    }

    /**
     * this method will validate/verify the token and return the subject
     * @param tokenJWT receives the token
     * @return the subject(user) --> .withSubject(usuario.getLogin()) in TokenService gerarToken()
     */
    public String getSubject(String tokenJWT) {

        try {
            var algoritmo = Algorithm.HMAC256(secret);

            return JWT.require(algoritmo)
                    .withIssuer(ISSUER) //API Voll.med
                    .build()
                    .verify(tokenJWT) //até esse ponto do código, vai verificar se o token é valido
                    .getSubject(); //caso o token seja válido, irá ser pego(get) o subject(getSubject)
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!: " + tokenJWT);
        }
    }

    /**
     * @return when the token will expire
     */
    private static ZonedDateTime dataExpiracao() {
        System.out.println("getDataExpiração: " + LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC));
        int JWTDuration = 2; //token duration in hours(2 = 2 hours)
        //use LocalDateTime and UTC, could generate conflicts, because the local
        //date time, could not be the same as UTC, so the time, won't be right
        //e.g., LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC)
        //=================================
        //get the current time(when token is generated) and add 2 hours
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(2);
    }


}