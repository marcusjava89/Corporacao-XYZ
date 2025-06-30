package com.javajuniordeepseek.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javajuniordeepseek.application.JavaJuniorDeepSeekApplication;
import com.javajuniordeepseek.model.Cliente;
import com.javajuniordeepseek.repository.ClienteRepository;

@SpringBootTest(classes = JavaJuniorDeepSeekApplication.class)
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;

	@Test
	public void testarBuscaNomeEncontrado() {
		
		System.out.println(" ");
		clienteRepository.deleteAll();

		Cliente cliente = new Cliente();
		cliente.setNome("Fulano da Silva");
		cliente.setEmail("fulano@email.com");
		cliente.setTelefone("11999999999");
		cliente.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente);

		Cliente cliente2 = new Cliente();
		cliente2.setNome("Marcus da Silva");
		cliente2.setEmail("marcusjava89@gmail.com");
		cliente2.setTelefone("21999625322");
		cliente2.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente2);

		Cliente cliente3 = new Cliente();
		cliente3.setNome("Jorge Jesus");
		cliente3.setEmail("jorgejesus@gmail.com");
		cliente3.setTelefone("2186450456");
		cliente3.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente3);

		List<Cliente> resposta = clienteRepository.buscaNome("silva");
		
		assertEquals("Fulano da Silva", resposta.get(0).getNome());
		assertEquals(2, resposta.size());

	}

	@Test
	public void testarNomeBuscaNaoEncontrado() {
		
		System.out.println(" ");
		clienteRepository.deleteAll();

		Cliente cliente = new Cliente();
		cliente.setNome("Fulano da Silva");
		cliente.setEmail("fulano@email.com");
		cliente.setTelefone("11999999999");
		cliente.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente);

		Cliente cliente2 = new Cliente();
		cliente2.setNome("Marcus da Silva");
		cliente2.setEmail("marcusjava89@gmail.com");
		cliente2.setTelefone("21999625322");
		cliente2.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente2);

		Cliente cliente3 = new Cliente();
		cliente3.setNome("Jorge Jesus");
		cliente3.setEmail("jorgejesus@gmail.com");
		cliente3.setTelefone("2186450456");
		cliente3.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente3);

		List<Cliente> resposta = clienteRepository.buscaNome("joca");

		assertEquals(0, resposta.size()); //Deve retorna uma lista vazia

	}
	
	@Test
	public void testarBuscarPorEmail() {
		
		System.out.println(" ");
		clienteRepository.deleteAll();

		Cliente cliente = new Cliente();
		cliente.setNome("Fulano da Silva");
		cliente.setEmail("fulano@email.com");
		cliente.setTelefone("11999999999");
		cliente.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente);

		Cliente cliente2 = new Cliente();
		cliente2.setNome("Marcus da Silva");
		cliente2.setEmail("marcusjava89@gmail.com");
		cliente2.setTelefone("21999625322");
		cliente2.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente2);

		Cliente cliente3 = new Cliente();
		cliente3.setNome("Jorge Jesus");
		cliente3.setEmail("jorgejesus@gmail.com");
		cliente3.setTelefone("2186450456");
		cliente3.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente3);
		
		Cliente clienteObtido = clienteRepository.buscaPorEmail(cliente.getEmail());
		Cliente clienteObtido2 = clienteRepository.buscaPorEmail(cliente2.getEmail());
		Cliente clienteObtido3 = clienteRepository.buscaPorEmail(cliente3.getEmail());
		
		assertEquals("Fulano da Silva", clienteObtido.getNome());
		assertEquals("Marcus da Silva", clienteObtido2.getNome());
		assertEquals("Jorge Jesus", clienteObtido3.getNome());
		
	}
	
	@Test
	public void testarBuscarPorEmailNaoEncontrado() {
		
		System.out.println(" ");
		clienteRepository.deleteAll();

		Cliente cliente = new Cliente();
		cliente.setNome("Fulano da Silva");
		cliente.setEmail("fulano@email.com");
		cliente.setTelefone("11999999999");
		cliente.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente);

		Cliente cliente2 = new Cliente();
		cliente2.setNome("Marcus da Silva");
		cliente2.setEmail("marcusjava89@gmail.com");
		cliente2.setTelefone("21999625322");
		cliente2.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente2);

		Cliente cliente3 = new Cliente();
		cliente3.setNome("Jorge Jesus");
		cliente3.setEmail("jorgejesus@gmail.com");
		cliente3.setTelefone("2186450456");
		cliente3.setDataDeCadastro(java.sql.Date.valueOf("2024-06-10"));
		clienteRepository.save(cliente3);
		
		//Cliente não encontrado porque não temos esse email no banco de dados.
		Cliente ClienteObtido = clienteRepository.buscaPorEmail("teste@gmail.com");
		
		assertEquals(null, ClienteObtido); 
	}
		
}





