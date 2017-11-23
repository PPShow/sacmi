package br.com.mimilis.sacmi.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TB_FIN_DESPESA")
public class Despesa extends MovimentoFinanceiro implements Serializable {

	private static final long serialVersionUID = 3694371023016931558L;
	
	public Despesa() {
		super();
		this.tipo = MovimentoFinanceiro.DESPESA;
	}
	
	public Despesa(Long id, String descricao, Calendar dataVigencia) {
		super(id, descricao, dataVigencia);
		this.tipo = MovimentoFinanceiro.DESPESA;
	}
	
	public Despesa(Long id, String descricao, Calendar dataVigencia, BigDecimal valor) {
		super(id, descricao, dataVigencia, valor);
		this.tipo = MovimentoFinanceiro.DESPESA;
	}
	
	public Despesa(Long id, String descricao, Calendar dataVigencia, Double valor) {
		super(id, descricao, dataVigencia, valor);
		this.tipo = MovimentoFinanceiro.DESPESA;
	}
}