package com.javajuniordeepseek.test;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javajuniordeepseek.application.JavaJuniorDeepSeekApplication;
import com.javajuniordeepseek.model.Cliente;
import com.javajuniordeepseek.repository.ClienteRepository;

import junit.framework.TestCase;


@SpringBootTest(classes = JavaJuniorDeepSeekApplication.class)
class JavaJuniorDeepSeekApplicationTests extends TestCase{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Test
	public void testarApiSalvarCliente() throws JsonProcessingException, Exception{
		
		clienteRepository.deleteAll();
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Cliente cliente = new Cliente();
		
		cliente.setNome("Marcus1");
		cliente.setEmail("marcusjava89@gmail.com");
		cliente.setTelefone("21999625322");
		cliente.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/salvarCliente")
				.content(mapper.writeValueAsString(cliente))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno da API "+retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("Retorno status: "+retornoApi.andReturn().getResponse().getStatus());
		
		Cliente objetoRetorno = mapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Cliente.class);
		
		assertEquals(cliente.getNome(), objetoRetorno.getNome());
		assertEquals(cliente.getAtivo(), objetoRetorno.getAtivo());
		assertEquals(cliente.getEmail(), objetoRetorno.getEmail());
		assertEquals(cliente.getTelefone(), objetoRetorno.getTelefone());
		assertEquals(cliente.getDataDeCadastro(), objetoRetorno.getDataDeCadastro());
		assertEquals(201, retornoApi.andReturn().getResponse().getStatus());
		
	}
	
	@Test
	public void testarApiDeletarCliente() throws JsonProcessingException, Exception{
						
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Marcus2");
		cliente.setEmail("marcusjpa6@hotmail.com");
		cliente.setTelefone("(21)8645-0456");
		cliente.setDataDeCadastro(java.sql.Date.valueOf("2025-09-10"));
		clienteRepository.save(cliente);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete("/deletarCliente")
				.content(mapper.writeValueAsString(cliente))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
		
		assertEquals("Cliente deletado com sucesso.", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
	}
	
	@Test
	public void testarApiDeletarPorId() throws JsonProcessingException, Exception{
						
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Marcus2");
		cliente.setEmail("marcusjpa6@hotmail.com");
		cliente.setTelefone("(21)8645-0456");
		cliente.setDataDeCadastro(java.sql.Date.valueOf("2025-09-10"));
		clienteRepository.save(cliente);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete("/deletarPorId/"+cliente.getId())
				.content(mapper.writeValueAsString(cliente))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
		
		assertEquals("Cliente deletado pelo id", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
	}
	
}














