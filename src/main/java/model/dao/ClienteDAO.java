package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Cliente;

public class ClienteDAO implements BaseDAO<Cliente>{

	@Override
	public Cliente salvar(Cliente novaEntidade) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO CLIENTE (nome, sobrenome, cpf, idendereco) VALUES (?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);

		try {
			stmt.setString(1, novaEntidade.getNome());
			stmt.setString(2, novaEntidade.getSobrenome());
			stmt.setString(3, novaEntidade.getCpf());
			stmt.setInt(4, novaEntidade.getEndereco().getIdEndereco());
			stmt.execute();
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novaEntidade.setIdCliente(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Cadastro do Usuário.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return novaEntidade;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM usuario WHERE idcliente = " + id;
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Exclusão do Cliente.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return (resultado > 0);
	}

	@Override
	public boolean alterar(Cliente entidade) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "UPDATE cliente SET nome = '" + entidade.getNome() + "', cpf = '" + entidade.getCpf()
				+ "' WHERE idusuario = " + entidade.getIdCliente();
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Atualização do Cliente.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return (resultado > 0);
	}

	@Override
	public Cliente consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cliente> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}


}
