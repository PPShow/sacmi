package br.com.mimilis.sacmi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_FIN_LIVRO_RAZAO")
public class LivroRazao implements Serializable {
	
	private static final long serialVersionUID = -8300170348501322709L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="COD_MES")
	private Integer mes;
	
	@Column(name="COD_ANO")
	private Integer ano;
	
	@Column(name="BALANCO_ATUAL")
	private BigDecimal balanco;
	
	//--- CONSTRUTORES
	public LivroRazao() { }
	
	public LivroRazao(Long id, Integer mes, Integer ano) {
		super();
		this.id = id;
		this.mes = mes;
		this.ano = ano;
		balanco = BigDecimal.ZERO;
	}
	
	public LivroRazao(Long id, Integer mes, Integer ano, BigDecimal balanco) {
		super();
		this.id = id;
		this.mes = mes;
		this.ano = ano;
		this.balanco = balanco;
	}
	
	//--- HASH-CODE & EQUALS
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
		LivroRazao other = (LivroRazao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	//--- GETTERS & SETTERS
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getMes() {
		return mes;
	}
	
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	
	public Integer getAno() {
		return ano;
	}
	
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public BigDecimal getBalanco() {
		return balanco;
	}
	
	public void setBalanco(BigDecimal balanco) {
		this.balanco = balanco;
	}
}
