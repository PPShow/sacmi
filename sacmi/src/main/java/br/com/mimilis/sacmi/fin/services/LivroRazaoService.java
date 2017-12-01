package br.com.mimilis.sacmi.fin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mimilis.sacmi.fin.domain.LivroRazao;
import br.com.mimilis.sacmi.fin.repositories.LivroRazaoRepository;
import br.com.mimilis.sacmi.geral.exceptions.ObjectNotFoundException;

@Service
public class LivroRazaoService {
	
	@Autowired
	private LivroRazaoRepository dao;
	
	public LivroRazao recuperar(Long id) {
		LivroRazao lr = dao.findOne(id);
		if(lr == null) {
			throw new ObjectNotFoundException("Livro Razão não encontrado - ID: "+id);
		}
		return lr;
	}

}