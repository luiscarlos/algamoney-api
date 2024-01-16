/**
 * 
 */
package com.algamoney.api.service.exception;

import org.springframework.beans.factory.annotation.Autowired;

import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.PessoaRepository;

/**
 * 
 */
public class PessoaInexistenteOuInativaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LancamentoRepository  lancamentoRepository;
	
	@Autowired
	private PessoaRepository  pessoaRepository;
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).get();
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
			
		}
		return lancamentoRepository.save(lancamento);
	}

}
