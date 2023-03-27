package com.fourheads.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fourheads.bookstore.domain.Categoria;
import com.fourheads.bookstore.dto.CategoriasDto;
import com.fourheads.bookstore.repositories.CategoriaRepository;
import com.fourheads.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = this.categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo:" + Categoria.class.getName()));
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria salvar(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Integer id, CategoriasDto dto) {
		Categoria categoria = findById(id);
		categoria.setDescricao(dto.getDescricao());
		categoria.setNome(dto.getNome());

		return categoriaRepository.save(categoria);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		}catch ( DataIntegrityViolationException e) {
			throw new com.fourheads.bookstore.service.exceptions.DataIntegrityViolationException("Categoria não pode ser deletado, possui livros associados.");
		}
	}
}
