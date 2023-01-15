package br.com.api.apiCadastro.model.dto;

import lombok.Getter;

@Getter
public class EnderecoResponseDTO {
    private Long id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Boolean principal;
}
