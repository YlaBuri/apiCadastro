package br.com.api.apiCadastro.model;



import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.api.apiCadastro.model.dto.PessoaRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Valid
    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    @JsonManagedReference
    private List<Endereco> enderecos;

    public Pessoa(String nome, Date dataNascimento, List<Endereco> enderecos) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }

    public Pessoa(PessoaRequestDTO pessoaDTO) {
        this.nome = pessoaDTO.getNome();
        this.dataNascimento = pessoaDTO.getDataNascimento();
        this.enderecos = pessoaDTO.getEnderecos();
    }
}