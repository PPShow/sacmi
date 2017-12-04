package br.com.mimilis.sacmi.fin.ref;

public enum TipoItemFinanceiro {
	
	REC_DINHEIRO			(1, 2, "Dinheiro",			'D', TipoFinanceiro.RECEITA)
	,REC_CARTAO_DEBITO		(2, 2, "Cartão de Débito",	'B', TipoFinanceiro.RECEITA)
	,REC_CARTAO_CREDITO		(3, 2, "Cartão de Crédito",	'C', TipoFinanceiro.RECEITA)
	,REC_VALE_REFEICAO		(4, 2, "Vale Refeição",		'R', TipoFinanceiro.RECEITA)
	,REC_VALE_ALIMENTACAO	(5, 2, "Vale Alimentação",	'A', TipoFinanceiro.RECEITA)
	
	,DESP_GERAL				(6, 3, "Despesa Geral",		'G', TipoFinanceiro.DESPESA)
	,DESP_COMPRA			(7, 3, "Ordem de Aquisição",'A', TipoFinanceiro.DESPESA)
	,DESP_CONTA				(8, 3, "Pagamento de Conta",'C', TipoFinanceiro.DESPESA)
	,DESP_PESSOAL			(9, 3, "Despesa Pessoal",	'P', TipoFinanceiro.DESPESA)
	,DESP_SALARIO			(10,3, "Pagamento de Salário",'S', TipoFinanceiro.DESPESA)
	;
	
	private Integer id;
	private Integer grupo;
	private String descricao;
	private Character valor;
	private TipoFinanceiro tipoFinanceiro;

	private TipoItemFinanceiro(Integer id, Integer grupo, String descricao, Character valor, TipoFinanceiro tipoFinanceiro) {
		this.id = id;
		this.grupo = grupo;
		this.descricao = descricao;
		this.valor = valor;
		this.tipoFinanceiro = tipoFinanceiro;
	}

	public Integer id() {
		return id;
	}

	public Integer grupo() {
		return grupo;
	}

	public String descricao() {
		return descricao;
	}

	public Character valor() {
		return valor;
	}

	public TipoFinanceiro tipoFinanceiro() {
		return tipoFinanceiro;
	}
	
	private static final int MODE_VALUE = 0;
	private static final int MODE_ID = 1;
	private static final int MODE_DESC = 2;
	private static TipoItemFinanceiro search(Object value, TipoFinanceiro tipo, int searchMode) {
		if(value == null) {
			return null;
		}
		
		if(tipo == null) {
			throw new IllegalArgumentException("Não se pode realizar uma busca sem definidor de tipos financeiros");
		}
		
		for(TipoItemFinanceiro item : values()) {
			if(item.tipoFinanceiro.equals(tipo)) {
				if(
						   (searchMode == MODE_VALUE && item.valor.equals(value) )
						|| (searchMode == MODE_ID && item.id.equals(value) )
						|| (searchMode == MODE_DESC && item.descricao.equals(value) )
					) {
					return item;
				}
			}
		}
		
		throw new IllegalArgumentException("Os parâmetros passados ["+value+", "+tipo+", "+searchMode+"] não correspondem a nenhum registro");
	}
	
	public static TipoItemFinanceiro recuperarReceita(Integer id) {
		return search(id, TipoFinanceiro.RECEITA, MODE_ID);
	}
	
	public static TipoItemFinanceiro recuperarReceita(Character valor) {
		return search(valor, TipoFinanceiro.RECEITA, MODE_VALUE);
	}
	
	public static TipoItemFinanceiro recuperarReceita(String descricao) {
		return search(descricao, TipoFinanceiro.RECEITA, MODE_DESC);
	}
	
	public static TipoItemFinanceiro recuperarDespesa(Integer id) {
		return search(id, TipoFinanceiro.DESPESA, MODE_ID);
	}
	
	public static TipoItemFinanceiro recuperarDespesa(Character valor) {
		return search(valor, TipoFinanceiro.DESPESA, MODE_VALUE);
	}
	
	public static TipoItemFinanceiro recuperarDespesa(String descricao) {
		return search(descricao, TipoFinanceiro.DESPESA, MODE_DESC);
	}
}
