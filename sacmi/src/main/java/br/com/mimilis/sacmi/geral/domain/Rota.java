package br.com.mimilis.sacmi.geral.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_GER_ROTA")
public class Rota implements Serializable {
	
	private static final long serialVersionUID = 127117186898155913L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="DESCRICAO", nullable=false)
	private String descricao;
	
	@Column(name="ENDERECO_INI")
	private String enderecoIni;
	
	@Column(name="ENDERECO_FIM")
	private String enderecoFim;
	
	@Column(name="TRAJETO_TOTAL", precision=5, scale=5)
	private BigDecimal trajetoTotal;

	//--- CONSTRUTORES
	public Rota(Long id, String descricao, String enderecoIni, String enderecoFim) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.enderecoIni = enderecoIni;
		this.enderecoFim = enderecoFim;
	}

	public Rota(Long id, String descricao, String enderecoIni, String enderecoFim, BigDecimal trajetoTotal) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.enderecoIni = enderecoIni;
		this.enderecoFim = enderecoFim;
		this.trajetoTotal = trajetoTotal;
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
		Rota other = (Rota) obj;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEnderecoIni() {
		return enderecoIni;
	}

	public void setEnderecoIni(String enderecoIni) {
		this.enderecoIni = enderecoIni;
	}

	public String getEnderecoFim() {
		return enderecoFim;
	}

	public void setEnderecoFim(String enderecoFim) {
		this.enderecoFim = enderecoFim;
	}

	public BigDecimal getTrajetoTotal() {
		return trajetoTotal;
	}

	public void setTrajetoTotal(BigDecimal trajetoTotal) {
		this.trajetoTotal = trajetoTotal;
	}
}
