package com.javajuniordeepseek.exception;

public class ClienteNotFoundException extends Exception {
	public ClienteNotFoundException() {
		super("Cliente não encontrado, pois não se encontra no banco de dados.");
	}
}
