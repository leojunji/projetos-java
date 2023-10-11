package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicosRepository;
import med.voll.api.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/medicos")
public class medicoController {

    @Autowired //irá instaciar este atributo de forma automatica
    private MedicosRepository repository;


    @PostMapping //qual o verbo do protocolo HTTP esse método irá realizar
    /***@RequestBody > indica que o parâmetro <String json> irá puxar o que tiver
     * no corpo da requisição - que neste caso é um json***/
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));

    }
}
