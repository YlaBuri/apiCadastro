package br.com.api.apiCadastro.repository;


import br.com.api.apiCadastro.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    public List<Endereco> findAllByPessoaId(Long id);
}