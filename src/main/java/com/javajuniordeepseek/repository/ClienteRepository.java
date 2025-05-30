package com.javajuniordeepseek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javajuniordeepseek.model.Cliente;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query("select u from Cliente u where upper(trim(u.nome)) like %?1%")
	List<Cliente> listagemClientes(String nome); 		
	
}
