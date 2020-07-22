package br.com.agenda.factory;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
	
	
	// nome usu�rio mysql
	private static final String USERNAME = "root";
	
	 
	// Senha
	private static final String PASSWORD = "";
	
	// host 
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	 /*
	  * conex�o com o banco de dados
	  */
	
	
	public static Connection createConnectionToMySQL() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = (Connection) DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		return connection;
		
	} 
	
	public static void main() throws Exception {
		
		// Recuperar uma conex�o com o banco de dados
		Connection conn = createConnectionToMySQL();
		 
		if(conn!=null) {
			System.out.println("Conec��o obtida com sucesso!");
			conn.close();
		}
		
		
	}
}
