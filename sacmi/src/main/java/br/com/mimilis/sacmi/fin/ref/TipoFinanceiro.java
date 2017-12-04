package br.com.mimilis.sacmi.fin.ref;

public enum TipoFinanceiro {
	
	RECEITA(1, 1, "Receita", 'R'),
	DESPESA(2, 1, "Despesa", 'D');
	
	private Integer id;
	private Integer grupo;
	private String descricao;
	private Character valor;

	private TipoFinanceiro(Integer id, Integer grupo, String descricao, Character valor) {
		this.id = id;
		this.grupo = grupo;
		this.descricao = descricao;
		this.valor = valor;
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
	
	public static TipoFinanceiro recuperar(Integer id) {
		if(id == null) {
			return null;
		}
		
		for(TipoFinanceiro tf : TipoFinanceiro.values()) {
			if(tf.id.equals(id)) {
				return tf;
			}
		}
		
		throw new IllegalArgumentException("Id ["+id+"] não encontrado");
	}
	
	public static TipoFinanceiro recuperar(Character valor) {
		if(valor == null) {
			return null;
		}
		
		for(TipoFinanceiro tf : TipoFinanceiro.values()) {
			if(tf.valor.equals(valor)) {
				return tf;
			}
		}
		
		throw new IllegalArgumentException("Valor '"+valor+"' não encontrado");
	}
}
