package com.edilson.cursoms.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.edilson.cursoms.domain.Cliente;
import com.edilson.cursoms.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Length(min=5, max=120, message="A quantidade deve estar entre 5 e 120 caractéres")
	@NotEmpty(message="Preencimento Obrigatório")
	private String nome;
	
	@NotEmpty(message="Preencimento Obrigatório")
	@Email(message="Email inválido!")
	private String email;
	
	public ClienteDTO() {}
	public ClienteDTO(Cliente cliente) {
		this.setId(cliente.getId());
		this.setNome(cliente.getNome());
		this.setEmail(cliente.getEmail()); 
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}


	
}
