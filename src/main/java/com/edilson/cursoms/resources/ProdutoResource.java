package com.edilson.cursoms.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edilson.cursoms.domain.Produto;
import com.edilson.cursoms.dtos.ProdutoDTO;
import com.edilson.cursoms.resources.utils.URL;
import com.edilson.cursoms.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService; 
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = produtoService.find(id);		
		return ResponseEntity.ok().body(obj);
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoDTO objDTO){
//		
////		objDTO = produtoService.insert(produtoService.fromDTO(objDTO)) Por que não posso fazer assim???
//		
//		Produto obj = produtoService.fromDTO(objDTO);
//		obj = produtoService.insert(obj);
//		
//		URI uri = ServletUriComponentsBuilder
//				.fromCurrentRequest()
//				.path("/{id}")
//				.buildAndExpand(obj
//				.getId())
//				.toUri();
//		
//		return ResponseEntity.created(uri).build();		
//	}
	
//	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
//	public ResponseEntity<Void> update(@Valid @RequestBody ProdutoDTO objDTO, @PathVariable Integer id) {
//		Produto obj = produtoService.fromDTO(objDTO);
//		obj.setId(id);
//		obj = produtoService.update(obj);
//		return ResponseEntity.noContent().build();
//	}
	
//	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable Integer id) {
//		this.produtoService.delete(id);
//		return ResponseEntity.noContent().build();
//	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	public ResponseEntity<List<ProdutoDTO>> findAll() {	
//		
//		return ResponseEntity.ok()
//				.body(this.produtoService.findAll()
//						.stream().map(cat -> new ProdutoDTO(cat))
//							.collect(Collectors.toList()));
//		
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="OrderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		
		List<Integer> ids = URL.decodeIntList(categorias);
		String nomeDecode = URL.decodeParam(nome);	
		Page<Produto> lista = produtoService.search(nomeDecode, ids, page, linesPerPage, orderBy, direction);
	  	Page<ProdutoDTO> listaDTO = lista.map(n -> new ProdutoDTO(n));
		return ResponseEntity.ok().body(listaDTO);
		
		
		
	}
	

	
}
