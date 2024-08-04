package med.voll.api.domain.medico.especialidade;

public record DadosEspecialidade (


        Long id,

        String nome,


        String descricao
)
{


    public DadosEspecialidade(Especialidade especialidade) {
        this(especialidade.getId(), especialidade.getNome(), especialidade.getDescricao());
    }
}
