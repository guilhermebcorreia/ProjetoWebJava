package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import models.ModelLogin;

public class daoUsuarioRepository {

	private Connection conexao;
	
	public daoUsuarioRepository() {
		conexao = Conexao.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin usuario) throws Exception {
		if (usuario.isNovo()) {
			String sql = "insert into usuario(nome, email, login, senha) values (?, ?, ?, ?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getLogin());
			stmt.setString(4, usuario.getSenha());
			stmt.execute();
		} else {
			String sql = "update usuario set nome = ?, email = ?, login = ?, senha = ? where idUser = ?";
			
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
	
	public List<ModelLogin> consultaUsuarioList(String nome) throws Exception {
		
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
								
		String sql = "select * from usuario where upper(nome) like upper(?)";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, "%" + nome + "%");
		
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setIdUser(res.getLong("iduser"));
			modelLogin.setNome(res.getString("nome"));
			modelLogin.setEmail(res.getString("email"));
			modelLogin.setLogin(res.getString("login"));
			modelLogin.setSenha(res.getString("senha"));
			
			lista.add(modelLogin);
			
		}
		
		return lista;
		
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
	
	public boolean validarUsuario(String login) throws SQLException {
		String sql = "select count(1) > 0 as exist from usuario where upper(login) = upper('" + login + "');";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		res.next();
		
		return res.getBoolean("exist");
	}
	
}
