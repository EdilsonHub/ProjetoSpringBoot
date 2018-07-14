package com.edilson.cursoms.services;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edilson.cursoms.domain.Cidade;
import com.edilson.cursoms.domain.Cliente;
import com.edilson.cursoms.domain.Endereco;
import com.edilson.cursoms.dtos.ClienteDTO;
import com.edilson.cursoms.dtos.ClienteNewDTO;
import com.edilson.cursoms.enums.TipoCliente;
import com.edilson.cursoms.repositories.CidadeRepository;
import com.edilson.cursoms.repositories.ClienteRepository;
import com.edilson.cursoms.repositories.EnderecoRepository;
import com.edilson.cursoms.services.exceptions.DataIntegrityException;
import com.edilson.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Collection<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return this.clienteRepository.findAll(pageRequest);
	}
	
	public Cliente update(Cliente newObj) {
		Cliente obj = this.find(newObj.getId());
		this.updateFromData(newObj, obj);
		return clienteRepository.save(obj);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(@Valid ClienteNewDTO objDTO) {
		Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cidade = cidadeRepository.findById(objDTO.getCidadeId()).orElse(null);
		Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			cliente.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cliente.getTelefones().add(objDTO.getTelefone3());
		}
		return cliente;
	}
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		Cliente cli = clienteRepository.save(obj);
		enderecoRepository.saveAll(cli.getEnderecos());
		
		return cli;
	}
	
	public void delete(Integer id) {
		this.find(id);
		try {
			this.clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir, porque possue entidades associadas!");
		}
	}
	private void updateFromData(Cliente newObj, Cliente obj) {
		obj.setNome(newObj.getNome());
		obj.setEmail(newObj.getEmail());
	}


}
