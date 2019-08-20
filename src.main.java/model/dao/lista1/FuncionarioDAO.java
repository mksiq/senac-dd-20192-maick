package model.dao.lista1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.lista1.Banco;
import model.entity.Telefone;
import model.entity.lista1.Diretor;
import model.entity.lista1.Funcionario;
import model.entity.lista1.Gerencia;
import model.entity.lista1.Gerente;
import model.entity.lista1.Lotacao;
import model.entity.lista1.Operacional;

public class FuncionarioDAO implements BaseDAO<Funcionario> {

	@Override
	public Funcionario salvar(Funcionario novoFuncionario) {
		Connection conexao = Banco.getConnection();
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
		Connection conn = Banco.getConnection();
		String sql = " SELECT ID, NOME, CPF, SEXO, IDADE, SALARIOBRUTO, COMISSAO, " + 
				" DESCONTOIMPOSTORENDA, DESCONTOPREVIDENCIA, SALARIOBASE, IDLOTACAO, CARGO "
				+ " FROM FUNCIONARIO "
				+ " WHERE ID=" + id;
		
		Statement stmt = Banco.getStatement(conn); 
		
		Funcionario funcionario= null;
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);
			
			if(resultadoDaConsulta.next()) {
				if (resultadoDaConsulta.getString("cargo").equals("Diretor")) {
					funcionario = new Diretor();
					funcionario = construirFuncionarioDoResultSet(resultadoDaConsulta);
				} else if (resultadoDaConsulta.getString("cargo").equals("Gerente")) {
					funcionario = new Gerente();
					funcionario = construirFuncionarioDoResultSet(resultadoDaConsulta);
				}
			} else {
				funcionario = new Operacional();
				funcionario = construirFuncionarioDoResultSet(resultadoDaConsulta);
			}
			if (resultadoDaConsulta.getInt("idlotacao") != 0) {
				LotacaoDAO lotDAO = new LotacaoDAO();
				Lotacao lot = lotDAO.consultarPorId(resultadoDaConsulta.getInt("idlotacao"));
				funcionario.setLotacao(lot);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Funcionario por id = " + id);
			System.out.println("Erro: " + e.getMessage());
		}
		
		return funcionario;
	}

	@Override
	public ArrayList<Funcionario> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Funcionario construirFuncionarioDoResultSet(ResultSet rs) {
		Funcionario f = null;

		try {
			if (rs.getString("cargo").equals("Diretor")) {
				f = new Diretor();
			} else if (rs.getString("cargo").equals("Gerente")) {
				f = new Gerente();
			} else {
				f = new Operacional();
			}
			
			f.setId(rs.getInt("id"));
			f.setNome(rs.getString("nome"));
			f.setCpf(rs.getString("cpf"));
			f.setSexo(rs.getString("sexo"));
			f.setIdade(rs.getInt("idade"));
			f.setSalarioBruto(rs.getDouble("salariobruto"));
			if(f instanceof Diretor) {
				((Diretor) f).setComissao(rs.getDouble("comissao"));
			} else if (f instanceof Gerente) {
				((Gerente) f).setComissao(rs.getDouble("comissao"));
			}
			f.setDescontoImpostoRenda(rs.getDouble("descontoImpostoRenda"));
			f.setDescontoPrevidencia(rs.getDouble("descontoPrevidencia"));
			f.setSalarioBase(rs.getDouble("salarioBase"));

			LotacaoDAO lotDAO = new LotacaoDAO();
//			if (rs.getInt("idlotacao") != 0) {
//				Lotacao lot = lotDAO.consultarPorId(rs.getInt("idlotacao"));
//				f.setLotacao(lot);
//			}
			

		} catch (SQLException e) {
			System.out.println("Erro ao construir funcionario a partir do ResultSet.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return f;
	}
	

	public ArrayList<Operacional> consultarTodosPorIdGerencia(int id) {
		Connection conn = Banco.getConnection();
		String sql = " SELECT ID, NOME, CPF, SEXO, IDADE, SALARIOBRUTO, COMISSAO, "
				+ "DESCONTOIMPOSTORENDA, DESCONTOPREVIDENCIA, SALARIOBASE, IDLOTACAO,"
				+ " CARGO  FROM FUNCIONARIO  WHERE IDLOTACAO=" + id;
		
		Statement stmt = Banco.getStatement(conn); 
		ArrayList<Operacional> funcionarios= new ArrayList<Operacional>();
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);
			Operacional funcionario = null;
			while(resultadoDaConsulta.next()) {
				if(resultadoDaConsulta.getString("Cargo").equals("Operacional")) {
					funcionario = (Operacional) construirFuncionarioDoResultSet(resultadoDaConsulta);
				}
				funcionarios.add(funcionario);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Funcionarios por idLotacao. idLotacao: " + id);
			System.out.println("Erro: " + e.getMessage());
		}
		
		return funcionarios;	

	}

}
