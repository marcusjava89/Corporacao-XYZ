package com.javajuniordeepseek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javajuniordeepseek.model.Cliente;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	//método antigo não buscou uma lista de nomes com parte do nome dado
	@Query("SELECT u FROM Cliente u WHERE UPPER(u.nome) "
			+ "LIKE UPPER(CONCAT('%', TRIM(:nome), '%'))")
	List<Cliente> buscaNome(@Param("nome") String nome);
	
	@Query("select u from Cliente u where trim(u.email) = ?1 ")
	Cliente buscaPorEmail(String email);
	
	@Query("select a.email from Cliente a")
	List<String> listaEmail();
}
