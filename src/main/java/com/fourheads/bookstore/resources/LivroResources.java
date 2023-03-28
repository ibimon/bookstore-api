package com.fourheads.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fourheads.bookstore.domain.Livro;
import com.fourheads.bookstore.dto.LivroDto;
import com.fourheads.bookstore.service.LivroService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/livros")
public class LivroResources {
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id){
		Livro obj = livroService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroDto>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer idCategoria){
		List<LivroDto> listaDto = livroService.findAll(idCategoria)
				.stream()
				.map(o -> new LivroDto(o))
					.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaDto); 
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Livro> adicionar(@Valid @RequestParam(value = "categoria", defaultValue = "0") Integer idCaegoria,
			@RequestBody Livro livro) {
		
		livro = livroService.salvar(idCaegoria, livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/livros/{id}")
				.buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		//Commite de testes vamos la
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LivroDto> update(@PathVariable Integer  id, @Valid @RequestBody LivroDto dto){
		Livro novo = livroService.update(id, dto);
		return ResponseEntity.ok().body(new LivroDto(novo));
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<LivroDto> updatePatch(@PathVariable Integer  id, @Valid @RequestBody LivroDto dto){
		Livro novo = livroService.update(id, dto);
		return ResponseEntity.ok().body(new LivroDto(novo));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
