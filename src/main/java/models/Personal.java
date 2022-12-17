package models;

public class Personal {
	private Long id;
	private String nome;
	private String card;
	
	public Personal() {
		super();
	}
	
	public boolean isNovo() {
		if (this.id == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Personal(String nome, String card) {
		super();
		this.nome = nome;
		this.card = card;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
	
}
