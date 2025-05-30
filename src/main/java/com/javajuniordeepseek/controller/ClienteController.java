package com.javajuniordeepseek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.data.repository.query.Param;
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

import com.javajuniordeepseek.model.Cliente;
import com.javajuniordeepseek.repository.ClienteRepository;


@Controller
@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping(value = "/salvarCliente") 
	@ResponseBody
	public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente){
		Cliente clienteSalvo = clienteRepository.save(cliente);
		return new ResponseEntity<Cliente>(clienteSalvo, HttpStatus.OK);
	}
	
	/*Deletar cliente.*/
	@DeleteMapping(value = "/deletarCliente")
	@ResponseBody
	public ResponseEntity<String> deletarCliente(@RequestBody Cliente cliente){
		clienteRepository.deleteById(cliente.getId());
		return new ResponseEntity<String>("Cliente deletado com sucesso.", HttpStatus.OK);
	}
	
	/*Deletar pelo id*/
	@DeleteMapping(value = "/deletarPorId/{id}")
	@ResponseBody
	public ResponseEntity<String> deletarPorId(@PathVariable("id") Long id){
		clienteRepository.deleteById(id);	
		return new ResponseEntity<String>("Cliente deletado pelo id", HttpStatus.OK);
	}
	
	/*Obter cliente por id*/
	@GetMapping(value = "/obterCliente/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> obterCliente(@PathVariable("id") Long id){
		
		Cliente clienteObtido = clienteRepository.findById(id).get();
		return new ResponseEntity<Cliente>(clienteObtido, HttpStatus.OK);
	}
	
	/*Listar clientes*/
	@GetMapping(value = "/listarClientes")
	@ResponseBody
	public ResponseEntity<List<Cliente>> listarClientes(){
		List<Cliente> listaClientes = clienteRepository.findAll();
		return new ResponseEntity<List<Cliente>>(listaClientes, HttpStatus.OK);
	}
	
	
	
}
