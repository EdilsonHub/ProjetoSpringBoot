package com.edilson.cursoms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edilson.cursoms.domain.Categoria;
import com.edilson.cursoms.dtos.CategoriaDTO;
import com.edilson.cursoms.repositories.CategoriaRepository;
import com.edilson.cursoms.services.exceptions.DataIntegrityException;
import com.edilson.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired // isto instacia a repo com injeção de dependência ou inversão de controles
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria newObj) {
		Categoria obj = this.find(newObj.getId());
		this.updateFromData(newObj, obj);
		return repo.save(obj);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			this.repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir categorias com produtos!");
		}
	}

	public List<Categoria> findAll() {
		return this.repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return this.repo.findAll(pageRequest);
	}
	public Categoria fromDTO(CategoriaDTO catDto) {
		return new Categoria(catDto.getId(),catDto.getNome());
	}
	
	private void updateFromData(Categoria newObj, Categoria obj) {
		obj.setNome(newObj.getNome());
	}
}







// Codigo para funcionar no java 7
// public Categoria buscar(Integer id) {
// Categoria obj = repo.findOne(id);
// return obj;
// }