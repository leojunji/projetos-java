package med.voll.api.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//para o spring carregar uma classe Java, deve sempre ter uma anotação indicando, nesse caso
//a  anotação é @RestControllerAdvice
@RestControllerAdvice //indica que esta classe serve para tratar erros
public class TratadorDeErros {





    /**
     * Ao usar essa classe, não é necessário usar o try catch dentro dos métodos das classes
     * controler(MedicoController, PacienteController...). Pois quando houver um erro será
     * consultado essa classe(por causa da anotação @RestControllerAdvice) para ver se ela já
     * está tratando o erro.
     * */


    /**
     * @ExceptionHandler(TIPO DE ERRO) = aqui, com o @ExceptionHandler configurado com o tipo de erro
     * quando houver esse erro EntityNotFoundException(e.g, quando o usuário digitar um id que não existe),
     * vai ser então chamado essa função.
     **/
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {

        return ResponseEntity.notFound().build();

    }


    /***
     * error 400 = is for a badRequest like: when you use the api to add(post)
     * an new Medico, and you forgive to put the phone(which is mandatory)
     * MethodArgumentNotValidException e = will receive the exception
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        /** body() = will receive the body content of the error, in this case. When the error 400
         occurs, the body is filled with too much information.
         So using body, we can put a personalized and maybe more precisely information about the error
         ##############################
         And here: erros.stream().map(e -> new DadosErrosValidacao(e)).toList() we:
         -- we are creating a new object: new DadosErrosValidacao(e)
         -- and transforming it into a list: toList()
         */
        return ResponseEntity.badRequest().body(erros.stream().map(e -> new DadosErrosValidacao(e)).toList());
    }


    /**
     * Isto é uma classe DTO <Data Transfer Object >, igual a DadosCadastroMedico por exemplo.
     * Contudo como ela só vai ser utilizada nessa classe, então esse DTO foi criado aqui mesmo
     * dentro da classe
     */
    public record DadosErrosValidacao(String campo, String mensagem) {

        public DadosErrosValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }


}
