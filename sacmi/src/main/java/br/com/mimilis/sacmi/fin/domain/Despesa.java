package br.com.mimilis.sacmi.fin.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="TB_FIN_DESPESA")
public class Despesa extends MovimentoFinanceiro implements Serializable {

	private static final long serialVersionUID = 3694371023016931558L;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="despesa", fetch=FetchType.LAZY,orphanRemoval=false)
	private List<DespesaItem> itens = new ArrayList<DespesaItem>();
	
	//--- CONSTRUTORES
	public Despesa() {
		super();
		this.tipo = MovimentoFinanceiro.DESPESA;
	}
	
	public Despesa(Long id, String descricao, Calendar dataCriacao) {
		super(id, descricao, dataCriacao);
		this.tipo = MovimentoFinanceiro.DESPESA;
	}
	
	public Despesa(Long id, String descricao, Calendar dataCriacao, BigDecimal valor) {
		super(id, descricao, dataCriacao, valor);
		this.tipo = MovimentoFinanceiro.DESPESA;
	}
	
	public Despesa(Long id, String descricao, Calendar dataCriacao, Double valor) {
		super(id, descricao, dataCriacao, valor);
		this.tipo = MovimentoFinanceiro.DESPESA;
	}

	//--- GETTERS & SETTERS
	public List<DespesaItem> getItens() {
		return itens;
	}

	public void setItens(List<DespesaItem> itens) {
		this.itens = itens;
	}
	
}
