package com.algamoney.api.service;



import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	
	@Autowired
	private PessoaRepository pessoaRepository;


	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
		
		if (!pessoaSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		//BeanUtils.copyProperties(pessoa, pessoaSalva,"codigo"); 
		pessoa.setCodigo(codigo);
  return pessoaRepository.save(pessoa);
  }
	
	
	public void atualizarPropriedadeAtivo(Long id_pessoa, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(id_pessoa);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

	private Pessoa buscarPessoaPeloCodigo(Long id_pessoa) {
	    Optional<Pessoa> pessoa = pessoaRepository.findById(id_pessoa);

	    return pessoa.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	/*
	  private Pessoa buscarPessoaPeloCodigo(Long id_pessoa) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id_pessoa);
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(id_pessoa); 
		if (pessoa == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return pessoa.get();
	}
	 */
}
