package com.edilson.cursoms.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.edilson.cursoms.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVancimento;
	
	public PagamentoComBoleto() {}

	public PagamentoComBoleto(Integer id, Pedido pedido, EstadoPagamento estado, Date dataDeVencimento, Date dataDePagamento) {
		super(id, pedido, estado);
		this.dataPagamento = dataDePagamento; //esta errado pois é private!
		this.dataVancimento = dataDeVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVancimento() {
		return dataVancimento;
	}

	public void setDataVancimento(Date dataVancimento) {
		this.dataVancimento = dataVancimento;
	}
	
	
}
