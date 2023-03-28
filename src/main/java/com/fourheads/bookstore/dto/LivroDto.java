package com.fourheads.bookstore.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fourheads.bookstore.domain.Livro;

import jakarta.validation.constraints.NotEmpty;

public class LivroDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message =  "Campo Titulo nao pode ser nulo nem vazio!")
	@Length(min = 3, max = 100, message = "Minimo 3 caracteres e no maximo 100 caracteres.")
	private String titulo;
	@NotEmpty(message =  "Campo Nome do Autor nao pode ser nulo nem vazio!")
	@Length(min = 3, max = 200, message = "Minimo 3 caracteres e no maximo 200 caracteres.")
	private String nomeAutor;	
	@NotEmpty(message =  "Campo Texto nao pode ser nulo nem vazio!")
	@Length(min = 3, max = 1200, message = "Minimo 3 caracteres e no maximo 1200 caracteres.")
	private String texto;
	
	public LivroDto(Livro obj) {
		super();
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
		this.nomeAutor = obj.getNomeAutor();
		this.texto = obj.getTexto();
	}
	public LivroDto() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getNomeAutor() {
		return nomeAutor;
	}
	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}
