package br.com.api.apiCadastro.service;


import br.com.api.apiCadastro.model.Pessoa;
import br.com.api.apiCadastro.model.dto.PessoaRequestDTO;
import br.com.api.apiCadastro.repository.EnderecoRepository;
import br.com.api.apiCadastro.repository.PessoaRepository;

import br.com.api.apiCadastro.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    private final EnderecoRepository enderecoRepository;

    public PessoaService(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(Long id){

        Optional<Pessoa> pessoa = Optional.ofNullable(pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada para o ID :: " + id)));

        return pessoa;
    }

    public Pessoa create(PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa = pessoaRequestDTO.toModel();
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void delete(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada para o ID :: " + id+" do Tipo :: "+Pessoa.class.getName()));

        pessoaRepository.delete(pessoa);
    }

    public Pessoa update(Long id, PessoaRequestDTO pessoa){
        return pessoaRepository.findById(id).map(
            p ->{
                p.setNome(pessoa.getNome());
                p.setDataNascimento(pessoa.getDataNascimento());
                p.setEnderecos(pessoa.getEnderecos());
                Pessoa pessoaUpdate = pessoaRepository.save(p);
                return  pessoaUpdate;
            }
        ).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada para o ID :: " + id));
    }
}
