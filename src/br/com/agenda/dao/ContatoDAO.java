package br.com.agenda.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	
	public void save(Contato contato) {
		
		String sql = "INSERT INTO contatos (nome, idade, datacadastro) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		 
		try {
			// Cria uma conexão com o DB
			conn = ConnectionFactory.createConnectionToMySQL();
			
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, (java.sql.Date) new Date(contato.getDataCadastro().getTime()));
			
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close(); 
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
		
	public List<Contato> getContatos(){
			
			String sql = "SELECT * FROM contatos ";
			
			List<Contato> contatos = new ArrayList<Contato>();
			
			Connection conn = null;
			PreparedStatement pstm = null;
			
			ResultSet rset= null;
			 
			try {
				// Cria uma conexão com o DB
				conn = ConnectionFactory.createConnectionToMySQL();
				
				
				pstm = (PreparedStatement) conn.prepareStatement(sql);

				
				rset = pstm.executeQuery();
				
				while(rset.next()) {
					Contato contato = new Contato();
					
					contato.setId(rset.getInt("id"));
					contato.setNome(rset.getString("nome"));
					contato.setIdade(rset.getInt("idade"));
					contato.setDataCadastro(rset.getDate("datacadastro"));
					
					contatos.add(contato);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				
				try {
					if(pstm!=null) {
						pstm.close();
					}
					if(conn!=null) {
						conn.close(); 
					}
				}catch(final Exception e) {
					e.printStackTrace();
				}
			}
			return contatos;
		}
}
