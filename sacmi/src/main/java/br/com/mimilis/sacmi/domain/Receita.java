package br.com.mimilis.sacmi.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TB_FIN_RECEITA")
public class Receita extends MovimentoFinanceiro implements Serializable {

	private static final long serialVersionUID = -343662104832083162L;
	
	//--- CONSTRUTORES
	public Receita() {
		super();
		this.tipo = MovimentoFinanceiro.RECEITA;
	}
	
	public Receita(Long id, String descricao, Calendar dataVigencia) {
		super(id, descricao, dataVigencia);
		this.tipo = MovimentoFinanceiro.RECEITA;
	}
	
	public Receita(Long id, String descricao, Calendar dataVigencia, BigDecimal valor) {
		super(id, descricao, dataVigencia, valor);
		this.tipo = MovimentoFinanceiro.RECEITA;
	}
	
	public Receita(Long id, String descricao, Calendar dataVigencia, Double valor) {
		super(id, descricao, dataVigencia, valor);
		this.tipo = MovimentoFinanceiro.RECEITA;
	}

	//--- GETTERS & SETTERS
	
}
