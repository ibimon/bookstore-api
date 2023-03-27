package com.fourheads.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourheads.bookstore.domain.Categoria;
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
