package models;

public class Financeiro {
	private Long id;
	private	String idUser;
	private String nome;
	private String valor;
	private String descricao;
	private String tipo;
	
	public Financeiro() {
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

	public Financeiro(Long id, String idUser, String valor, String descricao, String tipo) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.valor = valor;
		this.descricao = descricao;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
