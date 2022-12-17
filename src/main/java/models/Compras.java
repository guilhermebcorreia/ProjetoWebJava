package models;

public class Compras {
	private Long id;
	private String nome;
	private String valor;
	private String descricao;
	
	public Compras() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isNovo() {
		if (this.id == null) {
			return true;
		} else if (this.id != null && this.id > 0) {
			return false;
		}
		
		return id == null;
	}
	
	public Compras(Long id, String valor, String descricao) {
		super();
		this.id = id;
		this.valor = valor;
		this.descricao = descricao;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
