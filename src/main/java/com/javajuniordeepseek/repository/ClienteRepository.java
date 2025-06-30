package com.javajuniordeepseek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javajuniordeepseek.model.Cliente;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query("SELECT u FROM Cliente u WHERE UPPER(u.nome) LIKE UPPER(CONCAT('%', TRIM(:nome), '%'))")
	public List<Cliente> buscaNome(@Param("nome") String nome);
	
	@Query("select u from Cliente u where trim(u.email) = ?1 ")
	public Cliente buscaPorEmail(String email);
	
}
