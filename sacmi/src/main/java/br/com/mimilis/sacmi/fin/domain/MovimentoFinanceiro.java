package br.com.mimilis.sacmi.fin.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@MappedSuperclass
public abstract class MovimentoFinanceiro {
	
	public static final Character DESPESA = 'D';
	public static final Character RECEITA = 'R';
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	protected Long id;
	
	@Column(name="DESCRICAO", nullable=false)
	protected String descricao;
	
	/**
	 * Data atual
	 */
	@Column(name="DATA_CADASTRO")
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar dataCadastro;
	
	/**
	 * Data do ato do movimento - o início do cíclo
	 */
	@Column(name="DATA_CRIACAO", nullable=false)
	@Temporal(TemporalType.DATE)
	protected Calendar dataCriacao;
	
	@Column(name="VALOR", precision=6, scale=2, nullable=false)
	protected BigDecimal valor;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="ID_LIVRO_RAZAO")
	protected LivroRazao livroRazao;
	
	@Transient
	protected Character tipo;
	
	//--- CONSTRUCTORS
	public MovimentoFinanceiro() {
		super();
		this.valor = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public MovimentoFinanceiro(Long id, String descricao, Calendar dataCriacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.valor = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public MovimentoFinanceiro(Long id, String descricao, Calendar dataCriacao, BigDecimal valor) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.valor = valor;
	}
	
	public MovimentoFinanceiro(Long id, String descricao, Calendar dataCriacao, Double valor) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		
		if(valor != null) {
			this.valor = new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN);
		} else {
			this.valor = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
		}
	}

	//--- HASH CODE & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimentoFinanceiro other = (MovimentoFinanceiro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//--- METODOS AUXILIARES
	public void addValor(BigDecimal valor) {
		if(valor != null) {
			this.valor = this.valor.add(valor);
		}
	}
	
	public void addValor(Double valor) {
		if(valor != null) {
			this.valor = this.valor.add( new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN) );
		}
	}
	
	public boolean isReceita() {
		return RECEITA.equals(this.tipo);
	}
	
	public boolean isDespesa() {
		return DESPESA.equals(this.tipo);
	}

	//--- GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LivroRazao getLivroRazao() {
		return livroRazao;
	}

	public void setLivroRazao(LivroRazao livroRazao) {
		this.livroRazao = livroRazao;
	}
}