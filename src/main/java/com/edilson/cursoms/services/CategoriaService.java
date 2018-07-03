package com.edilson.cursoms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edilson.cursoms.domain.Categoria;
import com.edilson.cursoms.repositories.CategoriaRepository;
import com.edilson.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired // isto instacia a repo com injeção de dependência ou inversão de controles
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	// Codigo para funcionar no java 7
	// public Categoria buscar(Integer id) {
	// Categoria obj = repo.findOne(id);
	// return obj;
	// }
}