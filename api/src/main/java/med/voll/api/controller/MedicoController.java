package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import med.voll.api.medico.especialidade.DadosEspecialidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
     * ver arquivo  src/main/resources/testandoApi.txt***/
    public DadosListagemCompletaMedico cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        Medico medico = repository.save(new Medico(dados));
        return new DadosListagemCompletaMedico(repository.findById(medico.getId()).get());

    }







    /***
     * vai retornar as informações completas de um médico de acordo como id informado
     * ex: http://localhost:8080/medicos/id=3
     * */
    @GetMapping(value = "/id={id}")
    public DadosListagemCompletaMedico dadosMedicoById(@PathVariable Long id){
        return new DadosListagemCompletaMedico(repository.findById(id).get());


    }


    /*** Vai retornar os registros(ativos) dos medicos que tiverem o nome solicitado na url
     * ex: http://localhost:8080/medicos/nome=Gabriela?sort=especialidade.nome
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
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome","especialidade.nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);


    }

    @PutMapping //vai realizar o Update do CRUD
    @Transactional
    public DadosListagemCompletaMedico atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        /***
         * O getReferenceById() vai retornar os dados(do tipo Medico) apenas do ID solicitado
         * ver arquivo src/main/resources/testandoApi.txt***/
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return dadosMedicoById(dados.id());


    }

    @DeleteMapping("/id={id}") //as chaves {} indica que é um parâmetro dinâmico, ou seja, não é um valor específico
    @Transactional
    /***Este método vai deletar um registro de forma lógica, colocando ativo = 0 ou false
     * @PathVariable indica que o id irá receber um parâmetro da URL
     * ex: http://localhost:8080/medicos/id=2 ***/
    public String excluir(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
        return "O usuário " + medico.getNome() + " foi excluído com SUCESSO";
    }
}
