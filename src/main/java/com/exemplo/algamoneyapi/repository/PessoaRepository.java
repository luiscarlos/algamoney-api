package com.exemplo.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.algamoneyapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
