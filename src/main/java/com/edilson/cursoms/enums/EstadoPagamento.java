package com.edilson.cursoms.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pagamento Pendente"), 
	QUITADO(2, "Pagamento Quitado"),
	CANCELADO(3,"Pagamento Cancelado");

	private int cod;
	private String descrição;

	private EstadoPagamento(int cod, String descrição) {
		this.cod = cod;
		this.descrição = descrição;
	}

	public int getCod() {
		return cod;
	}

	public String getDescrição() {
		return descrição;
	}

	public static EstadoPagamento toEnum(Integer num) {
		if (num == null)
			return null;
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (num.equals(x.getCod()))
				return x;
		}
		throw new IllegalArgumentException("Id inválido: " + num);
	}

}
