package br.com.mimilis.sacmi.fin.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.mimilis.sacmi.fin.ref.TipoFinanceiro;
import br.com.mimilis.sacmi.geral.domain.Rota;

@Entity
@Table(name="TB_FIN_RECEITA")
public class Receita extends MovimentoFinanceiro implements Serializable {

	private static final long serialVersionUID = -343662104832083162L;
	
	@ManyToOne
	@JoinColumn(name="ID_ROTA", nullable=true)
	private Rota rota;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="receita", fetch=FetchType.LAZY, orphanRemoval=false)
	private List<ReceitaItem> itens = new ArrayList<ReceitaItem>();
	
	//--- CONSTRUTORES
	public Receita() {
		super();
		this.tipo = TipoFinanceiro.RECEITA;
	}
	
	public Receita (Long id, String descricao, Calendar dataCriacao) {
		super(id, descricao, dataCriacao);
		this.tipo = TipoFinanceiro.RECEITA;
	}
	
	public Receita (Long id, String descricao, Calendar dataCriacao, BigDecimal valor) {
		super(id, descricao, dataCriacao, valor);
		this.tipo = TipoFinanceiro.RECEITA;
	}
	
	public Receita (Long id, String descricao, Calendar dataCriacao, Double valor) {
		super(id, descricao, dataCriacao, valor);
		this.tipo = TipoFinanceiro.RECEITA;
	}
	
	//--- GETTERS & SETTERS
	public Rota getNomeRota() {
		return rota;
	}

	public void setNomeRota(Rota rota) {
		this.rota = rota;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public List<ReceitaItem> getItens() {
		return itens;
	}

	public void setItens(List<ReceitaItem> itens) {
		this.itens = itens;
	}
}
