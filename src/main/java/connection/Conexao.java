package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private static String db = "jdbc:mysql://localhost:3306/projetoWeb?autoReconnect=true";
	private static String user = "root";
	private static String senha = "administrador";
	private static Connection conexao = null;
	
	static {
		Conectar();
	}
	
	public static Connection getConnection() {
		return conexao;
	}
	
	public Conexao() {
		Conectar();
	}
	
	public static void Conectar() {
		try {
			if (conexao == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexao = DriverManager.getConnection(db, user, senha);
				conexao.setAutoCommit(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
