package br.com.api.apiCadastro.service;

import br.com.api.apiCadastro.model.Endereco;
import br.com.api.apiCadastro.model.Pessoa;
import br.com.api.apiCadastro.model.dto.PessoaRequestDTO;
import br.com.api.apiCadastro.repository.PessoaRepository;
import br.com.api.apiCadastro.service.PessoaService;
import br.com.api.apiCadastro.service.exceptions.ResourceNotFoundException;
import org.junit.Ignore;
import org.junit.Test;

import org.junit.Before;
import org.junit.After;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * PessoaService Tester.
 *
 * @author <Authors name>
 * @since <pre>jan 13, 2023</pre>
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {


    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    /**
     *
     * Method: findAll()
     *
     */
    @Test
    public void deveListarTodasAsPessoa() throws Exception {
        Pessoa pessoa1 = new Pessoa("Pessoa Teste", new Date(), Arrays.asList(new Endereco("Rua Teste", "12345678", "10", "Cidade Teste", true)));
        Pessoa pessoa2 = new Pessoa("Pessoa Teste 2", new Date(), Arrays.asList(new Endereco("Rua Teste", "12345678", "10", "Cidade Teste", true)));
        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);
        when(pessoaRepository.findAll()).thenReturn(pessoas);

        // when
        List<Pessoa> result = pessoaService.findAll();

        assertEquals(2, result.size());
        assertEquals(pessoa1, result.get(0));
        assertEquals(pessoa2, result.get(1));
    }

    /**
     *
     * Method: findById(Long id)
     *
     */
    @Test
    public void deveBuscarPessoaPorId() throws Exception {
        long idPessoa = 1L;
        Pessoa pessoa = new Pessoa("Pessoa Teste", new Date(), Arrays.asList(new Endereco("Rua Teste", "12345678", "10", "Cidade Teste", true)));
        when(pessoaRepository.findById(idPessoa)).thenReturn(Optional.of(pessoa));

        // when
        Optional<Pessoa> returnedPessoa = pessoaService.findById(idPessoa);

        // then
        assertTrue(returnedPessoa.isPresent());
        assertEquals(pessoa, returnedPessoa.get());
    }

    /**
     *
     * Method: findById(Long id)
     *
     */
    @Test(expected = ResourceNotFoundException.class)
    public void deveRotornarFalhaAoBuscarPessoaPorId() throws Exception {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        pessoaService.findById(1L);
    }

    /**
     *
     * Method: create(PessoaRequestDTO pessoaRequestDTO)
     *
     */
    @Test
    public void deveCriarPessoa() throws Exception {
        PessoaRequestDTO pessoaDTO = new PessoaRequestDTO("Pessoa Teste", new Date(), Arrays.asList(new Endereco("Rua Teste", "12345678", "10", "Cidade Teste", true)));
        Pessoa pessoa = new Pessoa(pessoaDTO);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa pessoaCriada = pessoaService.create(pessoaDTO);

        assertEquals(pessoa, pessoaCriada);
    }

    /**
     *
     * Method: delete(Long id)
     *
     */
    @Test
    public void deveDeletarPessoa() throws Exception {
        Pessoa pessoa = new Pessoa("Pessoa Teste", new Date(), Arrays.asList(new Endereco("Rua Teste", "12345678", "10", "Cidade Teste", true)));
        when(pessoaRepository.findById(1L)).thenReturn(java.util.Optional.of(pessoa));

        pessoaService.delete(1L);
    }

    /**
     *
     * Method: update(Long id, PessoaRequestDTO pessoa)
     *
     */
    @Test
    public void deveAtualizarPessoa() throws Exception {
        PessoaRequestDTO pessoaDTO = new PessoaRequestDTO("Pessoa Teste", new Date(), Arrays.asList(new Endereco("Rua Teste", "12345678", "10", "Cidade Teste", true)));
        Pessoa pessoa = new Pessoa(pessoaDTO);
        when(pessoaRepository.findById(1L)).thenReturn(java.util.Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa updatedPessoa = pessoaService.update(1L, pessoaDTO);

        assertEquals(pessoa, updatedPessoa);
    }


}
