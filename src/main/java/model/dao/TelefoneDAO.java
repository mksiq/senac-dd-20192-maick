package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.Telefone;

public class TelefoneDAO implements BaseDAO<Telefone>{

	@Override
	public Telefone salvar(Telefone novoTelefone) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO TELEFONE (codigoPais, ddd, numero, "
				+ "tipoLinha, idCliente, ativo) "
				+ "VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			stmt.setString(1, novoTelefone.getCodigoPais());
			stmt.setString(2, novoTelefone.getDdd());
			stmt.setString(3, novoTelefone.getNumero());
			stmt.setString(4, novoTelefone.getTipoLinha());
			stmt.setInt(5, novoTelefone.getIdCliente());
			stmt.setInt(6, novoTelefone.isAtivo() ? 1 : 0);
			stmt.execute();
			
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				int idGerado = generatedKeys.getInt(1);
				novoTelefone.setIdTelefone(idGerado);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo telefone.");
			System.out.println("Erro: " + e.getMessage());
		}

		return novoTelefone;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM telefone WHERE idtelefone = " + id;
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Exclusão do Telefone.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return (resultado > 0);
	}

	@Override
	public boolean alterar(Telefone entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Telefone consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Telefone> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
