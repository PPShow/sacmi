package br.com.mimilis.sacmi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mimilis.sacmi.domain.LivroRazao;
import br.com.mimilis.sacmi.repositories.LivroRazaoRepository;

@Service
public class LivroRazaoService {
	
	@Autowired
	private LivroRazaoRepository dao;
	
	public LivroRazao recuperar(Long id) {
		return dao.findOne(id);
	}

}
