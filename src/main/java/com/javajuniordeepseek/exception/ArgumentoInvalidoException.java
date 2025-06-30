package com.javajuniordeepseek.exception;

public class ArgumentoInvalidoException extends Exception {
	public ArgumentoInvalidoException() {
		super("Campo não não pode ser vazio.");
	}

	public ArgumentoInvalidoException(String campo) {
		super("O campo " + campo + " não pode ser vazio.");
	}

}
