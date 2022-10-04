package models;

public class ModelLogin {

	private Long idUser;
	private String nome;
	private String email;
	private String login;
	private String senha;
		
	public ModelLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean isNovo() {
		if (this.idUser == null) {
			return true;
		} else if (this.idUser != null && this.idUser > 0) {
			return false;
		}
		
		return idUser == null;
	}

	public ModelLogin(String nome, String email, String login, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}

	
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
		
}
