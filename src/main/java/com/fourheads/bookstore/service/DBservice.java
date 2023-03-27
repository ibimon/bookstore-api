package com.fourheads.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourheads.bookstore.domain.Categoria;
import com.fourheads.bookstore.domain.Livro;
import com.fourheads.bookstore.repositories.CategoriaRepository;
import com.fourheads.bookstore.repositories.LivroRepository;

@Service
public class DBservice {
	@Autowired
	private CategoriaRepository  categoriaRepository;
	@Autowired
	private LivroRepository livroRepository; 
	
	
	public void instanciaBaseDeDados() {
		Categoria c = new Categoria(null, "Informatica", "Livro de T.I");
		Livro l = new Livro(null, "Clean code", "Robert Martin", "L. IP.", c);
		
		c.getLivros().addAll(Arrays.asList(l));
		
		this.categoriaRepository.saveAll(Arrays.asList(c));
		this.livroRepository.saveAll(Arrays.asList(l));
	}
}
