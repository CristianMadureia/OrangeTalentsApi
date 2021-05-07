package br.cristian.zupteste.desafioorangetalents.handler;

import java.util.List;

public class FieldObjectError {

	private List<String> errors;
	
	public FieldObjectError(List<String> erros) {
		this.errors = erros;
	}
	
	public List<String> getErros() {
		return this.errors;
	}
	
	public String getMensagem() {
		return "Ocorreu erros na validação!";
	}
}
