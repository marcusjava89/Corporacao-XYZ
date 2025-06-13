package com.javajuniordeepseek.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.javajuniordeepseek.repository.ClienteRepository;

@DataJpaTest
public class ClienteRepositoryTest {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	
	@Test
	public void testarbuscaNome() {
		
	}
}
