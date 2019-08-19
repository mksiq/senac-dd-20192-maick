package model.dao.lista1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.aula05.EnderecoDAO;
import model.dao.aula05.TelefoneDAO;
import model.dao.lista1.Banco;
import model.entity.aula05.Cliente;
import model.entity.aula05.Endereco;
import model.entity.aula05.Telefone;
import model.entity.lista1.Diretor;
import model.entity.lista1.Funcionario;
import model.entity.lista1.Gerente;

public class FuncionarioDAO implements BaseDAO<Funcionario> {

	@Override
	public Funcionario salvar(Funcionario novoFuncionario) {
		Connection conexao = Banco.getConnection();
//		id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//1		nome VARCHAR(100),
//2		cpf VARCHAR(11),
//3		sexo CHAR,
//4		idade INT,
//5		salariobruto DOUBLE,
//6		comissao DOUBLE,
//7		descontoImpostoRenda DOUBLE,
//8		descontoPrevidencia DOUBLE,
//9		salarioBase DOUBLE,
//10	idlotacao INT,
//11	cargo VARCHAR(20), 
		String sql = " INSERT INTO FUNCIONARIO(NOME, CPF, SEXO, IDADE, SALARIOBRUTO, COMISSAO, "
				+ " DESCONTOIMPOSTORENDA, DESCONTOPREVIDENCIA, SALARIOBASE, IDLOTACAO, CARGO) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			stmt.setString(1, novoFuncionario.getNome());
			stmt.setString(2, novoFuncionario.getCpf());
			stmt.setString(3, novoFuncionario.getSexo());
			stmt.setInt(4, novoFuncionario.getIdade());
			stmt.setDouble(5, novoFuncionario.getSalarioBruto());
			if (novoFuncionario instanceof Diretor) {
				Diretor novoGestor = (Diretor) novoFuncionario; 
				stmt.setDouble(6, novoGestor.getComissao());
			} else if( novoFuncionario instanceof Gerente) {
				Gerente novoGestor = (Gerente) novoFuncionario; 
				stmt.setDouble(6, novoGestor.getComissao());
			} else {
				stmt.setDouble(6, 0);
			}
			stmt.setDouble(7, novoFuncionario.getDescontoImpostoRenda());
			stmt.setDouble(8, novoFuncionario.getDescontoPrevidencia());
			stmt.setDouble(9, novoFuncionario.getSalarioBase());
			if (novoFuncionario.getLotacao() != null) {
				stmt.setInt(10, novoFuncionario.getLotacao().getId());
			} else {
				stmt.setInt(10, 0);
			}
			if (novoFuncionario instanceof Diretor) {
				stmt.setString(11, "Diretor");
			} else if (novoFuncionario instanceof Gerente) {
				stmt.setString(11, "Gerente");
			} else {
				stmt.setString(11, "Operacional");
			}
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				int idGerado = rs.getInt(1);
				novoFuncionario.setId(idGerado);
				System.out.println("Funcionario de id " + idGerado + " inserido." );
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo Funcionario.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoFuncionario;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Funcionario entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Funcionario consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Funcionario> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Funcionario construirFuncionariDoResultSet(ResultSet rs) {
//		Funcionario f;
//		if (f instanceof Diretor) {
//			f = new Diretor();
//		} else if (f instanceof Gerente) {
//			f = new Gerente();
//		} 
//		try {
//			c.setId(rs.getInt("id"));
//			c.setNome(rs.getString("nome"));
//			c.setSobrenome(rs.getString("sobrenome"));
//			c.setCpf(rs.getString("cpf"));
//
//			EnderecoDAO enderecoDAO = new EnderecoDAO();
//			Endereco end = enderecoDAO.consultarPorId(rs.getInt("idendereco"));
//			c.setEndereco(end);
//			
//			TelefoneDAO telefoneDAO = new TelefoneDAO();
//			ArrayList<Telefone> telefones = telefoneDAO.consultarTodosPorIdCliente(rs.getInt("id"));
//			c.setTelefones(telefones);
//		} catch (SQLException e) {
//			System.out.println("Erro ao construir cliente a partir do ResultSet.");
//			System.out.println("Erro: " + e.getMessage());
//		}
//		
//		return null;
	}

}
