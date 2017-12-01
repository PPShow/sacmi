package br.com.mimilis.sacmi.fin.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mimilis.sacmi.fin.domain.LivroRazao;
import br.com.mimilis.sacmi.fin.services.LivroRazaoService;

@RestController
@RequestMapping(value="/livrosRazao")
public class LivroRazaoResource {

	@Autowired
	private LivroRazaoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		
		LivroRazao result = service.recuperar(id);
		return ResponseEntity.ok().body(result);
	}
}
