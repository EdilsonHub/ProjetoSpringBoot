package com.edilson.cursoms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edilson.cursoms.domain.Cliente;
import com.edilson.cursoms.repositories.ClienteRepository;
import com.edilson.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteServices {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
