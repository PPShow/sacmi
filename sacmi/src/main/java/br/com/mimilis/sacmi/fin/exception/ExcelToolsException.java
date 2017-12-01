package br.com.mimilis.sacmi.fin.exception;

public class ExcelToolsException extends RuntimeException {
	
	private static final long serialVersionUID = 377008370995345570L;
	
	public ExcelToolsException(String msg) {
		super(msg);
	}
	
	public ExcelToolsException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
