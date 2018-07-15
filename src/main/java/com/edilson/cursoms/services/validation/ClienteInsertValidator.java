package com.edilson.cursoms.services.validation;
//FONTE : https://gist.github.com/adrianoluis/5043397d378ae506d87366abb0ab4e30
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.edilson.cursoms.dtos.ClienteNewDTO;
import com.edilson.cursoms.enums.TipoCliente;
import com.edilson.cursoms.repositories.ClienteRepository;
import com.edilson.cursoms.resources.exception.FieldMessage;
import com.edilson.cursoms.services.validation.pacoteBR.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{

	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteInsertValidator() {}
		
	@Override
	public void initialize(ClienteInsert ann) {}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		if(clienteRepository.findByEmail(objDTO.getEmail()) != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for(FieldMessage e: list) {
			context.disableDefaultConstraintViolation();
			
			context.buildConstraintViolationWithTemplate(e.getMensagem())
				.addPropertyNode(e.getNome())
					.addConstraintViolation();
			
		}
		return list.isEmpty();
	}

}
