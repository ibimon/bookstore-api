package com.fourheads.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fourheads.bookstore.domain.Livro;
import com.fourheads.bookstore.dto.LivroDto;
import com.fourheads.bookstore.repositories.LivroRepository;
import com.fourheads.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class LivroService {
	@Autowired
	LivroRepository livroCategory;
	@Autowired
	CategoriaService categoriaService;

	public Livro findById(Integer id) {
		Optional<Livro> obj = this.livroCategory.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo:" + Livro.class.getName()));
	}

	public List<Livro> findAll() {
		return livroCategory.findAll();
	}

	public List<Livro> findAll(Integer idCategoria) {
		categoriaService.findById(idCategoria);
		
		return livroCategory.findAllByCategoria(idCategoria);
	}

	
	public Livro salvar(Integer idCategoria, Livro entidade) {
		entidade.setId(null);
		entidade.setCategoria(categoriaService.findById(idCategoria));
		return livroCategory.save(entidade);
	}

	public Livro update(Integer id, LivroDto dto) {
		Livro livro = findById(id);
		return updateData(dto, livro);
	}

	private Livro updateData(LivroDto dto, Livro livro) {
		livro.setTitulo(dto.getTitulo());
		livro.setTexto(dto.getTexto());
		livro.setNomeAutor(dto.getNomeAutor());
		
		return livroCategory.save(livro);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			livroCategory.deleteById(id);
		}catch ( DataIntegrityViolationException e) {
			throw new com.fourheads.bookstore.service.exceptions.DataIntegrityViolationException("Livro não pode ser deletado, possui livros associados.");
		}
	}
}
