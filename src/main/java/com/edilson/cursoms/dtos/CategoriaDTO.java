package com.edilson.cursoms.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.edilson.cursoms.domain.Categoria;


public class CategoriaDTO {
	
	private Integer id;
	
	@NotEmpty(message="O campo nome não pode ser vazio")
	@Length(min=5, max=80, message="a quantidade de caracteres deve estar entre 5 e 80")
	private String nome;
	
	public CategoriaDTO() {}
	public CategoriaDTO(Categoria categoria) {
		this.setId(categoria.getId());
		this.setNome(categoria.getNome());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
