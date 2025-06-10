package com.javajuniordeepseek.exception;

public class ClienteNotFoundException extends Exception {
	public ClienteNotFoundException() {
		super("Cliente não encontrado.");
	}
}
