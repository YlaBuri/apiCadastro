package br.com.api.apiCadastro.model.dto;



import br.com.api.apiCadastro.model.Endereco;
import br.com.api.apiCadastro.model.Pessoa;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class PessoaResponseDTO {
    private Long id;
    private String nome;
    private Date dataNascimento;
    private List<Endereco> enderecos;

    public PessoaResponseDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento();
        this.enderecos = pessoa.getEnderecos();
    }
}
