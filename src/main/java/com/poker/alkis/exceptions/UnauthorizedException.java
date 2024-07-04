package com.poker.alkis.exceptions;

public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = -1868606586030491327L;

	public UnauthorizedException(String message) {
		super(message);
	}
}
