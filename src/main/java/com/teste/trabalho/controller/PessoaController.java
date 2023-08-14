package com.teste.trabalho.controller;

import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.trabalho.model.Pessoa;
import com.teste.trabalho.repository.PessoaRepository;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
	@Autowired
	
	private PessoaRepository pessoaRepo;
	
	// listar tudo
	@GetMapping
	public List <Pessoa> findAll(){
		List<Pessoa> result =pessoaRepo.findAll();
		return result;
	}
	
	
	// -------- Buscar pelo id
	
			@GetMapping(value ="/{id}")
			public Pessoa findById(@PathVariable Long id) {
				
				Pessoa result = pessoaRepo.findById(id).get();
				return result;
	}
	
	
	
	// inserir
				@PostMapping
				public Pessoa insert(@RequestBody Pessoa pessoa) {
					
					Pessoa result = pessoaRepo.save(pessoa);
					return result;
		

				
				}
				//delete
				//@DeleteMapping(value ="/{id}")
				 @DeleteMapping("{id}")
				    public ResponseEntity<String> delete(@PathVariable("id")  Pessoa id){
				        pessoaRepo.delete(id);
				        return ResponseEntity.ok("Pessoa deletado com sucesso!.");
				    }
				
				
				 
				 @PutMapping("/{id}")
				    public Pessoa updateProduto(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizado) {
					 Pessoa pessoaExistente =pessoaRepo.findById(id).orElse(null);

				        if (pessoaExistente != null) {
				        	BeanUtils.copyProperties(pessoaAtualizado, pessoaExistente, "id");
				            return pessoaRepo.save(pessoaExistente);
				        } else {
				           return null; // Ou lançar uma exceção ou retornar uma mensagem de erro
				            
				            
				        }
				    }
				        }
				
		
				

