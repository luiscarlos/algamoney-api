package com.exemplo.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.algamoneyapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	
}
