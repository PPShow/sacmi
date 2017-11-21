package br.com.mimilis.sacmi.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mimilis.sacmi.domain.LivroRazao;

@RestController
@RequestMapping(value="/livrosRazao")
public class LivroRazaoResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<LivroRazao> listar() {
		
		List<LivroRazao> lista = new ArrayList<LivroRazao>();
		lista.add(new LivroRazao(1l, 10, 2017));
		lista.add(new LivroRazao(2l, 11, 2017));
		
		return lista;
	}
}
