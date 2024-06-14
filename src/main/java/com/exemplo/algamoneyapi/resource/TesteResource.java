package com.exemplo.algamoneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.exemplo.algamoneyapi.model.Categoria;
import com.exemplo.algamoneyapi.repository.CategoriaRepository;
import com.google.common.base.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/categoriasa")
public class TesteResource {

	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@GetMapping
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	
	@PostMapping()
	public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
		
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	
	/*@GetMapping("/{codigo}")
	public ResponseEntity<java.util.Optional<Categoria>> buscarPorId1(@PathVariable Long codigo){
		java.util.Optional<Categoria> categoria = categoriaRepository.findById(codigo);
		return categoria.isPresent()? ResponseEntity.ok(categoria):ResponseEntity.notFound().build();
		
	}*/
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPorId2(@PathVariable Long codigo){
		return categoriaRepository.findById(codigo)
				.map(categoria -> ResponseEntity.ok(categoria)).orElse(ResponseEntity.notFound().build());
	}
	
	
	
	
	
}
