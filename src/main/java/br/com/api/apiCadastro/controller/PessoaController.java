package br.com.api.apiCadastro.controller;


import br.com.api.apiCadastro.model.Pessoa;
import br.com.api.apiCadastro.model.dto.PessoaRequestDTO;
import br.com.api.apiCadastro.model.dto.PessoaResponseDTO;
import br.com.api.apiCadastro.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
@Api(value="API REST Pessoas")
public class PessoaController {

    private PessoaService pessoaService;


    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("pessoas")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva uma pessoa")
    public ResponseEntity<PessoaResponseDTO> create(@RequestBody PessoaRequestDTO pessoaDTO) {
        try {
            return ResponseEntity.ok().body(new PessoaResponseDTO(pessoaService.create(pessoaDTO)));
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("pessoas")
    @ApiOperation(value = "Retorna uma lista de pessoas")
    public ResponseEntity<List<Pessoa>> listar() {
        List<Pessoa> pessoas = pessoaService.findAll();
        return ResponseEntity.ok().body(pessoas);
    }

    @GetMapping("pessoas/{id}")
    @ApiOperation(value = "Retorna uma pessoa")
    public  ResponseEntity<PessoaResponseDTO> buscarPorId(@PathVariable Long id){
        Optional<Pessoa> pessoa = pessoaService.findById(id);
        if(pessoa.isPresent()){
            return ResponseEntity.ok(new PessoaResponseDTO(pessoa.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("pessoas/{id}")
    @ApiOperation(value = "Atualiza uma pessoa")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody PessoaRequestDTO pessoa) {
        try {
            return ResponseEntity.ok().body(new PessoaResponseDTO(pessoaService.update(id, pessoa)));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("pessoas/{id}")
    @ApiOperation(value = "Deleta uma pessoa")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            pessoaService.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
