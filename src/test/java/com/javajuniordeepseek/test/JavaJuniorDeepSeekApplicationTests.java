package com.javajuniordeepseek.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import com.fasterxml.jackson.core.type.TypeReference;
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
	
	@Test
	public void testarObterClienteEncontrado() throws JsonProcessingException, Exception{
		//Teste buscar e encontrar um cliente retornando o cliente pelo id
		clienteRepository.deleteAll();
						
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Marcus2");
		cliente.setEmail("marcusjpa6@hotmail.com");
		cliente.setTelefone("(21)8645-0456");
		cliente.setDataDeCadastro(java.sql.Date.valueOf("2025-09-10"));
		clienteRepository.save(cliente);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/obterCliente/"+cliente.getId())
				.content(mapper.writeValueAsString(cliente))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("Retorno status: "+retornoApi.andReturn().getResponse().getStatus());
		
		Cliente clienteObtido = mapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Cliente.class);
		
		assertEquals(cliente.getNome(), clienteObtido.getNome());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
	}
	
	@Test
	public void testarObterClienteNaoEncontrado() throws JsonProcessingException, Exception{
		
		//Testa o caso que cliente não é encontrado e retorna mensagem de não encontrado
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders
				.get("/obterCliente/"+400)
	            .accept(MediaType.APPLICATION_JSON)
	            .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isNotFound()); 

	        System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("Retorno status: "+retornoApi.andReturn().getResponse().getStatus());		
		
		assertEquals("Cliente não encontrado, pois não se encontra no banco de dados.", 
				retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(404, retornoApi.andReturn().getResponse().getStatus());
		
	}
	
	@Test
	public void testarListarClientes() throws JsonProcessingException, Exception{
		//Testa se é retornada uma lista de clientes.
		clienteRepository.deleteAll();
						
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Marcus2");
		cliente.setEmail("marcusjpa6@hotmail.com");
		cliente.setTelefone("(21)8645-0456");
		cliente.setDataDeCadastro(java.sql.Date.valueOf("2025-09-10"));
		clienteRepository.save(cliente);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders
				.get("/listarClientes")
				.content(mapper.writeValueAsString(cliente))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));
		
		//Desserializar a lista que é o objeto retornoApi
		
		List<Cliente> retornoApiList = mapper.readValue(retornoApi.andReturn()
				.getResponse()
				.getContentAsString(), new TypeReference<List<Cliente>>() {});
		
		assertEquals(1, retornoApiList.size());
		assertEquals(cliente.getNome(), retornoApiList.get(0).getNome());
		
	}
	
	@Test
	public void testarBuscarPorNome() throws JsonProcessingException, Exception{
		//Testa se é retornada uma lista com parte daquele nome.
		clienteRepository.deleteAll();
						
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Teste de busca por nome");
		cliente.setEmail("marcusjpa6@hotmail.com");
		cliente.setTelefone("(21)8645-0456");
		cliente.setDataDeCadastro(java.sql.Date.valueOf("2025-09-10"));
		clienteRepository.save(cliente);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders
				.get("/buscarPorNome/de")
				.content(mapper.writeValueAsString(cliente))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));
		
		//Desserializar a lista com os parte dos nomes que é o objeto retornoApi
		List<Cliente> retornoApiList = mapper.readValue(retornoApi.andReturn()
				.getResponse()
				.getContentAsString(), new TypeReference<List<Cliente>>() {});
		
		System.out.println("Retorno status: " + 
		retornoApi.andReturn().getResponse().getStatus());
		
		assertEquals(1, retornoApiList.size());
		
		
	}
		
}














