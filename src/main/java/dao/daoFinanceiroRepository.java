package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import models.Financeiro;

public class daoFinanceiroRepository {

private Connection conexao;
	
	public daoFinanceiroRepository() {
		conexao = Conexao.getConnection();
	}
	
	public Financeiro gravarFinanceiro(Financeiro financeiro) throws Exception {
		if (financeiro.isNovo()) {
			String sql = "insert into financeiro(idUser, nome, valor, descricao, tipo) values (?, ?, ?, ?, ?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, financeiro.getIdUser());
			stmt.setString(2, financeiro.getNome());
			stmt.setString(3, financeiro.getValor());
			stmt.setString(4, financeiro.getDescricao());
			stmt.setString(5, financeiro.getTipo());
			stmt.execute();
		} else {
			String sql = "update financeiro set idUser = ?, nome = ?, valor = ?, descricao = ?, tipo = ? where id = ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, financeiro.getIdUser());
			stmt.setString(2, financeiro.getNome());
			stmt.setString(3, financeiro.getValor());
			stmt.setString(4, financeiro.getDescricao());
			stmt.setString(5, financeiro.getTipo());
			stmt.setLong(6, financeiro.getId());
			stmt.executeUpdate();
		}
		conexao.commit();
		
		return this.consultarFinanceiro(financeiro.getNome());
	}
	
	public List<Financeiro> consultaFinanceiroList(String nome) throws Exception {
		
		List<Financeiro> lista = new ArrayList<Financeiro>();
								
		String sql = "select * from financeiro where upper(nome) like upper(?)";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, "%" + nome + "%");
		
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			
			Financeiro financeiro = new Financeiro();
			
			financeiro.setId(res.getLong("id"));
			financeiro.setIdUser(res.getString("idUser"));
			financeiro.setNome(res.getString("nome"));
			financeiro.setValor(res.getString("valor"));
			financeiro.setDescricao(res.getString("descricao"));
			financeiro.setTipo(res.getString("tipo"));
			
			lista.add(financeiro);
			
		}
		
		return lista;
		
	}
	
	public Financeiro consultarFinanceiro(String nome) throws Exception{
		Financeiro financeiro = new Financeiro();
		
		String sql = "select * from financeiro where upper(nome) = upper('" + nome + "')";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			financeiro.setId(res.getLong("id"));
			financeiro.setIdUser(res.getString("idUser"));
			financeiro.setNome(res.getString("nome"));
			financeiro.setValor(res.getString("valor"));
			financeiro.setDescricao(res.getString("descricao"));
			financeiro.setTipo(res.getString("tipo"));
		}
		
		return financeiro;
	}
	
	public boolean validarFinanceiro(String nome) throws SQLException {
		String sql = "select count(1) > 0 as exist from financeiro where upper(nome) = upper('" + nome + "');";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		res.next();
		
		return res.getBoolean("exist");
	}
	
	public Financeiro consultaFinanceiroID(String id) throws Exception {
			
			Financeiro financeiro = new Financeiro();
			
			String sql = "select * from financeiro where id = ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setLong(1, Long.parseLong(id));
			
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {  // Verificar se tem resultado
				
				financeiro.setId(res.getLong("id"));
				financeiro.setIdUser(res.getString("idUser"));
				financeiro.setNome(res.getString("nome"));
				financeiro.setValor(res.getString("valor"));
				financeiro.setDescricao(res.getString("descricao"));
				financeiro.setTipo(res.getString("tipo"));
				
			}
			
			return financeiro;
		}
	
	public void excluir(String id) throws Exception	{
		
		String sql = "delete from financeiro where id = ?;";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setLong(1, Long.parseLong(id));
		
		stmt.executeUpdate();
		
		conexao.commit();
		
		
		
	}
}
