package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Endereco;

public class EnderecoDAO implements BaseDAO<Endereco> {

	@Override
	public Endereco salvar(Endereco novoEndereco) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO ENDERECO(RUA, CEP, ESTADO, CIDADE, BAIRRO, NUMERO) "
				+ " VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			stmt.setString(1, novoEndereco.getRua());
			stmt.setString(2, novoEndereco.getCep());
			stmt.setString(3, novoEndereco.getEstado());
			stmt.setString(4, novoEndereco.getCidade());
			stmt.setString(5, novoEndereco.getBairro());
			stmt.setString(6, novoEndereco.getNumero());
			
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				int idGerado = rs.getInt(1);
				novoEndereco.setIdEndereco(idGerado);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo endereÃ§o.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoEndereco;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM endereco WHERE idendereco = " + id;
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Exclusão do Endereço.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return (resultado > 0);
	}

	@Override
	public boolean alterar(Endereco entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Endereco consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Endereco> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
