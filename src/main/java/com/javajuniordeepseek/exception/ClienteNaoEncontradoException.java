package com.javajuniordeepseek.exception;

public class ClienteNaoEncontradoException extends Exception {
	public ClienteNaoEncontradoException() {
		super("Cliente não encontrado, pois não se encontra no banco de dados.");
	}
}
