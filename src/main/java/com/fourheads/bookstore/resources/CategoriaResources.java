package com.fourheads.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fourheads.bookstore.domain.Categoria;
import com.fourheads.bookstore.dto.CategoriasDto;
import com.fourheads.bookstore.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResources {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id){
		Categoria obj = categoriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriasDto>> findAll(){
		List<CategoriasDto> listaDto = categoriaService.findAll()
				.stream()
				.map(o -> new CategoriasDto(o))
					.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaDto);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Categoria> adicionar(@RequestBody Categoria categoria) {
		categoria = categoriaService.salvar(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).body(categoria);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriasDto> update(@PathVariable Integer  id, @RequestBody CategoriasDto dto){
		Categoria novaCategoria = categoriaService.update(id, dto);
		return ResponseEntity.ok().body(new CategoriasDto(novaCategoria));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
//localhost:8080/categorias/1
