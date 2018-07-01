package com.edilson.cursoms.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edilson.cursoms.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResources {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria categoria1 = new Categoria(1,"Informática");
		Categoria categoria2 = new Categoria(1,"Escritório");
		List<Categoria> lista = new ArrayList<Categoria>();
		
		lista.add(categoria1);
		lista.add(categoria2);
//		lista.forEach(n);
		
		
		return lista;
	}
}
