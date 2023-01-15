package br.com.api.apiCadastro.controller;

import br.com.api.apiCadastro.model.Endereco;
import br.com.api.apiCadastro.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Api(value="API REST Pessoas")
public class EnderecoController {

    private final EnderecoService enderecoService;


    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("enderecos/pessoas/{id}")
    @ApiOperation(value = "Retorna a lista de endereços de uma pessoa")
    public ResponseEntity<List<Endereco>> ListarEnderecoPessoa(@PathVariable Long id){
        List<Endereco> enderecos = enderecoService.listByPessoa(id);
        return ResponseEntity.ok().body(enderecos);
    }


}
