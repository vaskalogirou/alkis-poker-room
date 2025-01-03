package com.poker.alkis.exceptions;

import java.io.Serial;

public class UnauthorizedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1868606586030491327L;

    public UnauthorizedException(String message) {
        super(message);
    }
}
