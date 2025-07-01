package com.javajuniordeepseek.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javajuniordeepseek.exception.ClienteNaoEncontradoException;
import com.javajuniordeepseek.model.Cliente;
import com.javajuniordeepseek.repository.ClienteRepository;

import jakarta.validation.Valid;

@Controller
@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping(value = "/salvarCliente") 
	@ResponseBody
	public ResponseEntity<?> salvarCliente(@Valid @RequestBody Cliente cliente) { 
		
		Cliente clienteObtido = clienteRepository.buscaPorEmail(cliente.getEmail());
		
		if(clienteObtido != null) {
			return new ResponseEntity<String>("Email já utilizado por outro cliente.", 
					HttpStatus.CONFLICT);
		}
		
		cliente.setAtivo(true);
		cliente.setDataDeCadastro(new Date());
		
		Cliente clienteNovo = clienteRepository.save(cliente);
		return new ResponseEntity<Cliente>(clienteNovo, HttpStatus.CREATED);
		
	}
	
	/*Deletar cliente.*/
	@DeleteMapping(value = "/deletarCliente")
	@ResponseBody
	public ResponseEntity<String> deletarCliente(@RequestBody Cliente cliente){ //Teste feito
		clienteRepository.deleteById(cliente.getId());
		return new ResponseEntity<String>("Cliente deletado com sucesso.", HttpStatus.OK);
	}
	
	/*Deletar pelo id*/
	@DeleteMapping(value = "/deletarPorId/{id}")
	@ResponseBody
	public ResponseEntity<String> deletarPorId(@PathVariable("id") Long id){ //Teste feito
		clienteRepository.deleteById(id);	
		return new ResponseEntity<String>("Cliente deletado pelo id", HttpStatus.OK);
	}
	
	@GetMapping(value = "/obterCliente/{id}")
	@ResponseBody
	public ResponseEntity<?> obterCliente(@PathVariable("id") Long id) throws 
	ClienteNaoEncontradoException{
		
		Cliente clienteObtido = clienteRepository
				.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException());
		return new ResponseEntity<Cliente>(clienteObtido, HttpStatus.OK);
	}
	
	/*Listar clientes*/
	@GetMapping(value = "/listarClientes")
	@ResponseBody
	public ResponseEntity<List<Cliente>> listarClientes(){ //Teste feito
		List<Cliente> listaClientes = clienteRepository.findAll();
		return new ResponseEntity<List<Cliente>>(listaClientes, HttpStatus.OK);
	}
	
	/*Buscar por cliente com parte do nome.*/
	@GetMapping(value = "/buscarPorNome/{nome}")
	@ResponseBody
	public ResponseEntity<?> buscarPorNome(@PathVariable("nome") String nome){ //Teste feito
		List<Cliente> listaDeEncontrados = clienteRepository.buscaNome(nome);
		return new ResponseEntity<List<Cliente>>(listaDeEncontrados, HttpStatus.OK);
	}
	
	
	
}



