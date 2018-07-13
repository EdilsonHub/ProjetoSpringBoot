package com.edilson.cursoms.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nome;
	private String mensagem;
	
	public FieldMessage() {}

	public FieldMessage(String nome, String mensagem) {
		super();
		this.setNome(nome);
		this.setMensagem(mensagem);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	

}
