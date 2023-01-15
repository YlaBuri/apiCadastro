package br.com.api.apiCadastro.model.dto;


import br.com.api.apiCadastro.model.Endereco;
import br.com.api.apiCadastro.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequestDTO {
    private String nome;
    private Date dataNascimento;
    private List<Endereco> enderecos;

    public Pessoa toModel() {
        return new Pessoa(nome, dataNascimento, enderecos);
    }
}
