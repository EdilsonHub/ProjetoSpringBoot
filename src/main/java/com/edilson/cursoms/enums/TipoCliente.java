package com.edilson.cursoms.enums;

public enum TipoCliente {
	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(1,"Pessoa Jurídica");
	
	private int cod;
	private String descrição;
	
	private TipoCliente(int cod, String descrição) {
		this.cod = cod;
		this.descrição = descrição;
	}

	public int getCod() {
		return cod;
	}

	public String getDescrição() {
		return descrição;
	}
	
	public static TipoCliente toEnum(Integer num) {
		if(num == null) return null;
		for(TipoCliente x: TipoCliente.values()) {
			if(num.equals(x.getCod())) return x;
		}
		throw new IllegalArgumentException("Id inválido: "+num);
	}
	
}
