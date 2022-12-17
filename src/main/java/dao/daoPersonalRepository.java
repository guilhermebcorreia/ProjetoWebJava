package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import models.Personal;

public class daoPersonalRepository {
	private Connection conexao;
	
	public daoPersonalRepository() {
		conexao = Conexao.getConnection();
	}
	
	public Personal gravar(Personal personal) throws Exception {
		if (personal.isNovo()) {
			String sql = "insert into personal(nome, card) values (?, ?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, personal.getNome());
			stmt.setString(2, personal.getCard());
			stmt.execute();
		} else {
			String sql = "update personal set nome = ?, card = ? where id = ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, personal.getNome());
			stmt.setString(2, personal.getCard());
			stmt.setLong(3, personal.getId());
			stmt.executeUpdate();
		}
		conexao.commit();
		
		return this.consultar(personal.getNome());
	}
	
	public List<Personal> consultaPersonalList(String nome) throws Exception {
		
		List<Personal> lista = new ArrayList<Personal>();
								
		String sql = "select * from personal where upper(nome) like upper(?)";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, "%" + nome + "%");
		
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			
			Personal Personal = new Personal();
			
			Personal.setId(res.getLong("id"));
			Personal.setNome(res.getString("nome"));
			Personal.setCard(res.getString("card"));
			
			lista.add(Personal);
			
		}
		
		return lista;
		
	}
	
	public Personal consultar(String nome) throws Exception{
		Personal Personal = new Personal();
		
		String sql = "select * from personal where upper(nome) = upper('" + nome + "')";
		System.out.print(sql);
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			Personal.setId(res.getLong("id"));
			Personal.setNome(res.getString("nome"));
			Personal.setCard(res.getString("card"));
		}
		
		return Personal;
	}
	
	public boolean validar(String nome) throws SQLException {
		String sql = "select count(1) > 0 as exist from personal where upper(nome) = upper('" + nome + "');";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		res.next();
		
		return res.getBoolean("exist");
	}
	
	
	public Personal consultaID(String id) throws Exception {
			
			Personal Personal = new Personal();
			
			String sql = "select * from personal where id = ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setLong(1, Long.parseLong(id));
			
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {  // Verificar se tem resultado
				
				Personal.setId(res.getLong("id"));
				Personal.setNome(res.getString("nome"));
				Personal.setCard(res.getString("card"));
			}
			
			return Personal;
		}
	
	public void excluir(String id) throws Exception	{
		
		String sql = "delete from personal where id= ?;";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setLong(1, Long.parseLong(id));
		
		stmt.executeUpdate();
		
		conexao.commit();
		
		
		
	}
}
