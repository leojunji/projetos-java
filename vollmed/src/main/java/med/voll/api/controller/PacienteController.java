package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.DadosListagemCompletaPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {

    @Autowired //irá instanciar este atributo de forma automatica
    private PacientesRepository repository; //repository da tabela medico


    @PostMapping //qual o verbo do protocolo HTTP esse método irá realizar, neste caso vai realizar o Create do CRUD
    /***@RequestBody > indica que o parâmetro <String json> irá puxar o que tiver
     * no corpo da requisição - que neste caso é um json***/
    @Transactional //Colocado quando o método salva, atualiza e/ou exclui dados de um banco de dados
    /***Este método irá adicionar um novo registro
     * ver arquivo  src/main/resources/testandoApi.txt
     * Aqui, deve-se retornar o código 201, contudo ele tem algumas regras, para o que se deve retornar:
     * - O código 201
     * - Os dados do novo registro/recurso criado que geralmente é do tipo DTO <Data Transfer Object>
     * - Cabeçalho do protcolo HTTP, que se chama Location
     * ***/
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente = new Paciente(dados);
        repository.save(paciente);

        /*aqui é o Location
        /{id} recebe o medico.getId()
        //aqui vai ser a url(e.g, http://localhost:8080/medicos/id=12
        */
        var uri = uriComponentsBuilder.path("/pacientes/id={id}").buildAndExpand(paciente.getId()).toUri();


        //new DadosListagemCompletaPaciente(medico) é os dados do novo registro criado
        return ResponseEntity.created(uri).body(new DadosListagemCompletaPaciente(paciente));

    }


    @GetMapping
//    codigo 200
    public ResponseEntity<Page<DadosListagemCompletaPaciente>> listar(@PageableDefault(size = 10, sort = {"nome","cpf"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCompletaPaciente::new);
        return ResponseEntity.ok(page);

    }

//    @PutMapping
//    @Transactional
//    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
//        var paciente = repository.getReferenceById(dados.id());
//        paciente.atualizarInformacoes(dados);
//
//        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }



}
