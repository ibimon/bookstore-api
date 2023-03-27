package com.fourheads.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fourheads.bookstore.domain.Livro;
public interface LivroRepository extends JpaRepository<Livro, Integer>{

	@Query(" Select o From Livro o Where o.categoria.id = :idCategoria ORDER BY o.titulo")
	List<Livro> findAllByCategoria(@Param(value = "idCategoria") Integer idCategoria);

}
