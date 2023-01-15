package br.com.api.apiCadastro.model.dto;


import br.com.api.apiCadastro.model.Endereco;
import lombok.Getter;

@Getter
public class EnderecoRequestDTO {
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Boolean principal;

    public Endereco toModel(){
        return new Endereco(logradouro, cep, numero, cidade, principal);
    }
}
