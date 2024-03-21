package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCrm() {
        return crm;
    }

    public String getEmail() {
        return email;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void atualizarInformacoes(DadosAtualizarMedico json) {
        if (json.nome() != null) {
            this.nome = json.nome();
        }
        if (json.telefone() != null) {
            this.telefone = json.telefone();
        }
        if (json.endereco() != null) {
            this.endereco.atualizarInformacoes(json.endereco());
        }
        if (json.nome() != null) {
            this.nome = json.nome();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
