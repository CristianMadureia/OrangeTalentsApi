package br.cristian.zupteste.desafioorangetalents.exceptions;

import org.springframework.http.HttpStatus;

public class AddressRegisterException extends Exception {

	private HttpStatus errorCode;
    private String message;

    public AddressRegisterException(HttpStatus errorCode, String message) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }
}