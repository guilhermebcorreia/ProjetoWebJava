package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import models.ModelLogin;

public class daoUsuarioRepository {

	private Connection conexao;
	
	public daoUsuarioRepository() {
		conexao = Conexao.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin usuario) throws Exception {
		if (usuario.isNovo()) {
			String sql = "inser into usuario(nome, email, login, senha) values (?, ?, ?, ?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getLogin());
			stmt.setString(4, usuario.getSenha());
			stmt.execute();
		} else {
			String sql = "update usuario set nome = ?, email = ?, login = ?, senha = ? where idUser";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getLogin());
			stmt.setString(4, usuario.getSenha());
			stmt.setLong(5, usuario.getIdUser());
			stmt.executeUpdate();
		}
		conexao.commit();
		
		return this.consultarUsuario(usuario.getLogin());
	}
	
	public boolean validarUsuario(ModelLogin model) throws Exception {
		String sql = "SELECT * FROM usuario WHERE login = ? ";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setLong(1, model.getIdUser());
		stmt.setString(2, model.getNome());
		stmt.setString(3, model.getEmail());
		stmt.setString(4, model.getLogin());
		stmt.setString(5, model.getSenha());
		
		ResultSet resultset = stmt.executeQuery();
		
		if (resultset.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public ModelLogin consultarUsuario(String login) throws Exception{
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from usuario where upper(login) = upper('" + login + "')";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			modelLogin.setIdUser(res.getLong("idUser"));
			modelLogin.setNome(res.getString("nome"));
			modelLogin.setEmail(res.getString("email"));
			modelLogin.setLogin(res.getString("login"));
			modelLogin.setSenha(res.getString("senha"));
		}
		
		return modelLogin;
	}
	
}
