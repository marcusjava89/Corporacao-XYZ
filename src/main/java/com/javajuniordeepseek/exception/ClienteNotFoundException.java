package com.javajuniordeepseek.exception;

public class ClienteNotFoundException extends Exception {
	public ClienteNotFoundException(Long id) {
		super("Cliente com o ID "+id+" não concontrado.");
	}
}
