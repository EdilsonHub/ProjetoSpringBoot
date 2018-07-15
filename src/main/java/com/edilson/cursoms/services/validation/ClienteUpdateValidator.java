package com.edilson.cursoms.services.validation;
//FONTE : https://gist.github.com/adrianoluis/5043397d378ae506d87366abb0ab4e30
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.edilson.cursoms.domain.Cliente;
import com.edilson.cursoms.dtos.ClienteDTO;
import com.edilson.cursoms.repositories.ClienteRepository;
import com.edilson.cursoms.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private HttpServletRequest request;
	
	public ClienteUpdateValidator() {}
		
	@Override
	public void initialize(ClienteUpdate ann) {}

	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);  
		
		Integer urlId = Integer.parseInt(map.get("id"));
		Cliente clienteAux = clienteRepository.findByEmail(objDTO.getEmail());
		
		//System.out.println("||||||||||||||||||||||||||||||Id que vem na url: " + urlId + "Id que vem do banco: " + clienteAux.getId());
		if( clienteAux != null && !clienteAux.getId().equals(urlId) ) {
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
