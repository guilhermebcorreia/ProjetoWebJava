package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.Conexao;
import models.ModelLogin;

public class DaoLoginRepository {

	private Connection conexao;
	
	public DaoLoginRepository() {
		conexao = Conexao.getConnection();
	}
	
	public boolean verify(ModelLogin model) throws Exception {
		String sql = "select * from usuario where login = ? and senha = ?";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, model.getLogin());
		stmt.setString(2, model.getSenha());
		
		ResultSet resultset = stmt.executeQuery();
		
		if (resultset.next()) {
			return true;
		} else {
			return false;
		}
	}
}
