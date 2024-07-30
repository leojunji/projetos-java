package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import med.voll.api.medico.especialidade.DadosEspecialidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {

    @Autowired //irá instanciar este atributo de forma automatica
    private MedicosRepository repository; //repository da tabela medico


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
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder){
        Medico medico = new Medico(dados);
        repository.save(medico);

        /*aqui é o Location
        /{id} recebe o medico.getId()
        //aqui vai ser a url(e.g, http://localhost:8080/medicos/id=12
        */
        var uri = uriComponentsBuilder.path("/medicos/id={id}").buildAndExpand(medico.getId()).toUri();


        //new DadosListagemCompletaMedico(medico) é os dados do novo registro criado
        return ResponseEntity.created(uri).body(new DadosListagemCompletaMedico(medico));

    }







    /***
     * vai retornar as informações completas de um médico de acordo como id informado
     * ex: http://localhost:8080/medicos/id=3
     * código 200
     * */
    @GetMapping(value = "/id={id}")
    public ResponseEntity dadosMedicoById(@PathVariable Long id){
        var medico = repository.findById(id).get();

        return ResponseEntity.ok(new DadosListagemCompletaMedico(medico));

    }


    /*** Vai retornar os registros(ativos) dos medicos que tiverem o nome solicitado na url
     * ex: http://localhost:8080/medicos/nome=Gabriela?sort=especialidade.nome
     * código 200
     *
    * */
    @GetMapping(value = "/nome={nome}")
    public Page<DadosListagemMedico> dadosMedicoByNome(@PathVariable String nome,@PageableDefault(size = 10) Pageable paginacao) {
        return repository.findByNome(nome, paginacao).map(DadosListagemMedico::new);

    }






    @GetMapping//vai realizar o Read do CRUD
    /***
     * O parâmetro paginacao pode receber argumentos da URL e retornar os dados de acordo com os argumentos
     * ex: medicos?size=1 retorna apenas 1 registro do banco de dados
     * ex: medicos?sort=nome vai ordenar os registros pelo nome do medico
     * ex: medicos?sort=especialidade.nome vai ordenar os registros pelo nome da especialidade
     * ex: http://localhost:8080/medicos?sort=especialidade.nome
     * @PageableDefault vai ter uma padrão de retorno caso o usuário não mude
     * Este método retorna um Page, pois o tipo page retorna além dos dados, informações como quantidade de dados, etc...
     * Retorna apenas os registros ativos = 1 ou true
     * ***/
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome","especialidade.nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping //vai realizar o Update do CRUD
    @Transactional
    public ResponseEntity<DadosListagemCompletaMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        /***
         * O getReferenceById() vai retornar os dados(do tipo Medico) apenas do ID solicitado
         * ver arquivo src/main/resources/testandoApi.txt
         * codigo 200
         * ***/
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosListagemCompletaMedico(medico));


    }

    @DeleteMapping("/id={id}") //as chaves {} indica que é um parâmetro dinâmico, ou seja, não é um valor específico
    @Transactional
    /***Este método vai deletar um registro de forma lógica, colocando ativo = 0 ou false
     * @PathVariable indica que o id irá receber um parâmetro da URL
     * ex: http://localhost:8080/medicos/id=2 ***/
    public ResponseEntity excluir(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
        /***
         * Aqui, está retornando um ResponseEntity.noContent().build()(codigo 204). Que é o retorno mais comum ao excluir algo
         * pois, no front-end por exemplo, pegamos a response e com base nela, colocamos  a reposta
         * Mas da para retornar uma String, indicando qual o usário foi excluido.
         * */
//        return "O usuário " + medico.getNome() + " foi excluído com SUCESSO";
        return ResponseEntity.noContent().build();
    }
}
