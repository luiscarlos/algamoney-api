package com.exemplo.algamoneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exemplo.algamoneyapi.event.RecursoCriadoEvent;
import com.exemplo.algamoneyapi.model.Categoria;
import com.exemplo.algamoneyapi.repository.CategoriaRepository;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	//public ResponseEntity<?> listar(){
	public List<Categoria> listar(){
		//List<Categoria> categorias = categoriaRepository.findAll();
		//return !categorias.isEmpty()? ResponseEntity.ok(categorias):ResponseEntity.notFound().build();
		//return !categorias.isEmpty()? ResponseEntity.ok(categorias):ResponseEntity.notContet().build();
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
	java.util.Optional<Categoria> categoria = this.categoriaRepository.findById(codigo);
	return categoria.isPresent() ? 
	        ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	}
	
	/*@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo1(@PathVariable Long codigo) {
		return this.categoriaRepository.findById(codigo)
		  .map(categoria -> ResponseEntity.ok(categoria))
		  .orElse(ResponseEntity.notFound().build());
		}
	*/

}
