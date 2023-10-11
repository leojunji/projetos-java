package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicosRepository;
import med.voll.api.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/medicos")
public class medicoController {

    @Autowired //irá instaciar este atributo de forma automatica
    private MedicosRepository repository;


    @PostMapping //qual o verbo do protocolo HTTP esse método irá realizar
    /***@RequestBody > indica que o parâmetro <String json> irá puxar o que tiver
     * no corpo da requisição - que neste caso é um json***/
    @Transactional //quando o método salva, atualiza e/ou exclui dados de um banco de dados
    /***Este método irá adicionar um novo registro***/
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));

    }

    @GetMapping
    public List<DadosListagemMedico> listar(){
        return repository.findAll().stream().map(repository -> new DadosListagemMedico(repository)).toList();

    }
}
