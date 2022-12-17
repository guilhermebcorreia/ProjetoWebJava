package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import models.Compras;

public class daoComprasRepository {

private Connection conexao;
	
	public daoComprasRepository() {
		conexao = Conexao.getConnection();
	}
	
	public Compras gravarcompra(Compras compra) throws Exception {
		if (compra.isNovo()) {
			String sql = "insert into compras(nome, valor, descricao) values (?, ?, ?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, compra.getNome());
			stmt.setString(2, compra.getValor());
			stmt.setString(3, compra.getDescricao());
			stmt.execute();
		} else {
			String sql = "update compras set nome = ?, valor = ?, descricao = ? where id = ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, compra.getNome());
			stmt.setString(2, compra.getValor());
			stmt.setString(3, compra.getDescricao());
			stmt.setLong(4, compra.getId()); 
			stmt.executeUpdate();
		}
		conexao.commit();
		
		return this.consultar(compra.getNome());
	}
	
	public List<Compras> consultaCompraList(String nome) throws Exception {
		
		List<Compras> lista = new ArrayList<Compras>();
								
		String sql = "select * from compras where upper(nome) like upper(?)";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, "%" + nome + "%");
		
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			
			Compras Compras = new Compras();
			
			Compras.setId(res.getLong("id"));
			Compras.setNome(res.getString("nome"));
			Compras.setValor(res.getString("valor"));
			Compras.setDescricao(res.getString("descricao"));
			
			lista.add(Compras);
			
		}
		
		return lista;
		
	}
	
	public Compras consultar(String nome) throws Exception{
		Compras Compras = new Compras();
		
		String sql = "select * from compras where upper(nome) = upper('" + nome + "')";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			Compras.setId(res.getLong("id"));
			Compras.setNome(res.getString("nome"));
			Compras.setValor(res.getString("valor"));
			Compras.setDescricao(res.getString("descricao"));
		}
		
		return Compras;
	}
	
	public boolean validar(String nome) throws SQLException {
		String sql = "select count(1) > 0 as exist from compras where upper(nome) = upper('" + nome + "');";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		res.next();
		
		return res.getBoolean("exist");
	}
	
	public Compras consultaID(String id) throws Exception {
			
			Compras Compras = new Compras();
			
			String sql = "select * from compras where id = ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setLong(1, Long.parseLong(id));
			
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {  // Verificar se tem resultado
				
				Compras.setId(res.getLong("id"));
				Compras.setNome(res.getString("nome"));
				Compras.setValor(res.getString("valor"));
				Compras.setDescricao(res.getString("descricao"));
				
			}
			
			return Compras;
		}
	
	public void excluir(String id) throws Exception	{
		
		String sql = "delete from compras where id = ?;";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setLong(1, Long.parseLong(id));
		
		stmt.executeUpdate();
		
		conexao.commit();
		
		
		
	}
	
}

