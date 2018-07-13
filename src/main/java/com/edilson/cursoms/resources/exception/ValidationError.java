package com.edilson.cursoms.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> eerrors = new ArrayList<>();	

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}


	
	public void setEerrors(List<FieldMessage> eerrors) {
		this.eerrors = eerrors;
	}

	public List<FieldMessage> getEerrors() {
		return eerrors;
	}

	public void AddError(String nomeCampo, String mensagem) {
		this.eerrors.add(new FieldMessage(nomeCampo,mensagem));
	}

}
