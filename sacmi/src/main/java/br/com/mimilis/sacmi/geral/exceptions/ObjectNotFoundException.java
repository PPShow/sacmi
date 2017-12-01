package br.com.mimilis.sacmi.geral.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -4699011311335631616L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
