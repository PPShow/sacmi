package br.com.mimilis.sacmi.fin.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.mimilis.sacmi.fin.ref.TipoItemFinanceiro;

@Entity
@Table(name="TB_FIN_DESPESA_ITEM")
public class DespesaItem implements Serializable{
	
	private static final long serialVersionUID = -7414301305980954172L;
	
	public static final Character CAT_GERAL = 'G';
	public static final Character CAT_COMPRA = 'C';
	public static final Character CAT_CONTA = 'T';
	public static final Character CAT_PESSOAL = 'P';
	public static final Character CAT_PAGAMENTO_SALARIO = 'S';
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="ID_DESPESA")
	private Despesa despesa;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="CATEGORIA")
	private Character categoria;
	
	/**
	 * Data prevista da saida de recursos - PODE SER A MESMA DA CRIACAO
	 */
	@Column(name="DATA_PREVISTA_PAGAMENTO", nullable=true)
	@Temporal(TemporalType.DATE)
	private Calendar dataPrevistaPagamento;
	
	/**
	 * Data real da saida de recursos - PODE SER A MESMA DA CRIACAO
	 */
	@Column(name="DATA_PAGAMENTO", nullable=true)
	@Temporal(TemporalType.DATE)
	private Calendar dataPagamento;
	
	@Column(name="VALOR_PAGO")
	private BigDecimal valorPago;
	
	@Column(name="VALOR_JUROS")
	private BigDecimal valorJuros;
	
	@Column(name="VALOR_TOTAL")
	private BigDecimal valorTotal;
	
	//--- CONSTRUTORES
	public DespesaItem() { }

	public DespesaItem(Long id, Despesa despesa, TipoItemFinanceiro categoria, BigDecimal valorPago) {
		super();
		this.id = id;
		this.despesa = despesa;
		this.categoria = categoria.valor();
		this.valorPago = valorPago;
		
		if(despesa != null) {
			this.dataPrevistaPagamento = despesa.getDataCriacao();
			this.dataPagamento = despesa.getDataCriacao();
		}
	}
	
	public DespesaItem(Long id, Despesa despesa, TipoItemFinanceiro categoria, Double valorPago) {
		super();
		this.id = id;
		this.despesa = despesa;
		this.categoria = categoria.valor();
		this.valorPago = valorPago != null ? new BigDecimal(valorPago).setScale(2, RoundingMode.HALF_EVEN) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
		
		if(despesa != null) {
			this.dataPrevistaPagamento = despesa.getDataCriacao();
			this.dataPagamento = despesa.getDataCriacao();
		}
	}

	public DespesaItem(Long id, Despesa despesa, String descricao, TipoItemFinanceiro categoria, Calendar dataPagamento,
			BigDecimal valorPago) {
		super();
		this.id = id;
		this.despesa = despesa;
		this.descricao = descricao;
		this.categoria = categoria.valor();
		this.dataPagamento = dataPagamento;
		this.valorPago = valorPago;
	}

	public DespesaItem(Long id, Despesa despesa, String descricao, TipoItemFinanceiro categoria, Calendar dataPrevistaPagamento,
			Calendar dataPagamento, BigDecimal valorPago, BigDecimal valorJuros, BigDecimal valorTotal) {
		super();
		this.id = id;
		this.despesa = despesa;
		this.descricao = descricao;
		this.categoria = categoria.valor();
		this.dataPrevistaPagamento = dataPrevistaPagamento;
		this.dataPagamento = dataPagamento;
		this.valorPago = valorPago;
		this.valorJuros = valorJuros;
		this.valorTotal = valorTotal;
	}

	//--- GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoItemFinanceiro getCategoria() {
		return TipoItemFinanceiro.recuperarDespesa(categoria);
	}

	public void setCategoria(TipoItemFinanceiro categoria) {
		if(categoria == null) {
			this.categoria = null;
		}
		this.categoria = categoria.valor();
	}

	public Calendar getDataPrevistaPagamento() {
		return dataPrevistaPagamento;
	}

	public void setDataPrevistaPagamento(Calendar dataPrevistaPagamento) {
		this.dataPrevistaPagamento = dataPrevistaPagamento;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(BigDecimal valorJuros) {
		this.valorJuros = valorJuros;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
