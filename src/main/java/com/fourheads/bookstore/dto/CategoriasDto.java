package com.fourheads.bookstore.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fourheads.bookstore.domain.Categoria;

import jakarta.validation.constraints.NotEmpty;

public class CategoriasDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message =  "Campo nome nao pode ser nulo nem vazio!")
	@Length(min = 3, max = 100, message = "Minimo 3 caracteres e no maximo 100 caracteres.")
	private String nome;
	@NotEmpty(message =  "Campo descrição nao pode ser nulo nem vazio!")
	@Length(min = 3, max = 200, message = "Minimo 3 caracteres e no maximo 200 caracteres.")
	private String descricao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public CategoriasDto(Categoria obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
	}
	public CategoriasDto() {
		super();
	}
	
	
}
