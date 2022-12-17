package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import models.Manutencao;

public class daoManutencaoRepository {

private Connection conexao;
	
	public daoManutencaoRepository() {
		conexao = Conexao.getConnection();
	}
	
	public Manutencao gravarmanutencao(Manutencao manutencao) throws Exception {
		if (manutencao.isNovo()) {
			String sql = "insert into manutencao(nome, descricao) values (?, ?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, manutencao.getNome());
			stmt.setString(2, manutencao.getDescricao());
			stmt.execute();
		} else {
			String sql = "update manutencao set nome = ?, descricao = ? where id = ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, manutencao.getNome());
			stmt.setString(2, manutencao.getDescricao());
			stmt.setLong(3, manutencao.getId());
			stmt.executeUpdate();
		}
		conexao.commit();
		
		return this.consultarmanutencao(manutencao.getNome());
	}
	
	public List<Manutencao> consultamanutencaoList(String nome) throws Exception {
		
		List<Manutencao> lista = new ArrayList<Manutencao>();
								
		String sql = "select * from manutencao where upper(nome) like upper(?)";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, "%" + nome + "%");
		
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			
			Manutencao manutencao = new Manutencao();
			
			manutencao.setId(res.getLong("id"));
			manutencao.setNome(res.getString("nome"));
			manutencao.setDescricao(res.getString("descricao"));
			lista.add(manutencao);
			
		}
		
		return lista;
		
	}
	
	public Manutencao consultarmanutencao(String nome) throws Exception{
		Manutencao manutencao = new Manutencao();
		
		String sql = "select * from manutencao where upper(nome) = upper('" + nome + "')";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			manutencao.setId(res.getLong("id"));
			manutencao.setNome(res.getString("nome"));
			manutencao.setDescricao(res.getString("descricao"));
		}
		
		return manutencao;
	}
	
	public boolean validarmanutencao(String nome) throws SQLException {
		String sql = "select count(1) > 0 as exist from manutencao where upper(nome) = upper('" + nome + "');";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		res.next();
		
		return res.getBoolean("exist");
	}
	
	public Manutencao consultamanutencaoID(String id) throws Exception {
			
			Manutencao manutencao = new Manutencao();
			
			String sql = "select * from manutencao where id = ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setLong(1, Long.parseLong(id));
			
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {  // Verificar se tem resultado
				
				manutencao.setId(res.getLong("id"));
				manutencao.setNome(res.getString("nome"));
				manutencao.setDescricao(res.getString("descricao"));
				
			}
			
			return manutencao;
		}
	
	public void excluir(String id) throws Exception	{
		
		String sql = "delete from manutencao where id = ?;";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setLong(1, Long.parseLong(id));
		
		stmt.executeUpdate();
		
		conexao.commit();
		
		
		
	}
}
