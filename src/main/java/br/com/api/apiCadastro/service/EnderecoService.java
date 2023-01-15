package br.com.api.apiCadastro.service;

import br.com.api.apiCadastro.model.Endereco;
import br.com.api.apiCadastro.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listByPessoa(Long id){
        return enderecoRepository.findAllByPessoaId(id);
    }
}
