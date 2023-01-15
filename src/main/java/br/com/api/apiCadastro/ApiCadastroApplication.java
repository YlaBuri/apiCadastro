package br.com.api.apiCadastro;

import br.com.api.apiCadastro.model.Endereco;
import br.com.api.apiCadastro.model.Pessoa;
import br.com.api.apiCadastro.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class ApiCadastroApplication implements CommandLineRunner {

	private final PessoaRepository pessoaRepository;

	public ApiCadastroApplication(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiCadastroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Endereco endereco1 = new Endereco("Rua A", "12345678", "1234", "cidade", true);
		Endereco endereco2 = new Endereco("Rua B", "87654321", "5678", "cidade", false);
		Endereco endereco3 = new Endereco("Rua C", "11111111", "2222", "cidade", true);
		Endereco endereco4 = new Endereco("Rua D", "22222222", "3333", "cidade", false);

		Pessoa pessoa1 = new Pessoa("João", new Date(), Arrays.asList( endereco1, endereco2));
		Pessoa pessoa2 = new Pessoa("Maria", new Date(), Arrays.asList(endereco3, endereco4));

		pessoaRepository.saveAll(Arrays.asList(pessoa1,pessoa2));

	}
}
