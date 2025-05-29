package com.javajuniordeepseek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javajuniordeepseek.model.Clientes;

public interface ClienteRepository extends JpaRepository<Clientes, Long>{

}
