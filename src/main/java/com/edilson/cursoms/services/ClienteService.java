package com.edilson.cursoms.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edilson.cursoms.domain.Cliente;
import com.edilson.cursoms.dtos.ClienteDTO;
import com.edilson.cursoms.repositories.ClienteRepository;
import com.edilson.cursoms.services.exceptions.DataIntegrityException;
import com.edilson.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Collection<Cliente> findAll() {
		return this.repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return this.repo.findAll(pageRequest);
	}
	
	public Cliente update(Cliente newObj) {
		Cliente obj = this.find(newObj.getId());
		this.updateFromData(newObj, obj);
		return repo.save(obj);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public void delete(Integer id) {
		this.find(id);
		try {
			this.repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir, porque possue entidades associadas!");
		}
	}
	private void updateFromData(Cliente newObj, Cliente obj) {
		obj.setNome(newObj.getNome());
		obj.setEmail(newObj.getEmail());
	}

}
