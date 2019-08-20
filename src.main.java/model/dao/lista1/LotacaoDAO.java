package model.dao.lista1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.lista1.Banco;

import model.entity.lista1.Diretor;
import model.entity.lista1.Diretoria;
import model.entity.lista1.Funcionario;
import model.entity.lista1.Gerencia;
import model.entity.lista1.Gerente;
import model.entity.lista1.Lotacao;
import model.entity.lista1.Operacional;

public class LotacaoDAO implements BaseDAO<Lotacao> {


	@Override
	public Lotacao salvar(Lotacao novaLotacao) {
		Connection conexao = Banco.getConnection();
		String sql = "INSERT INTO LOTACAO(nome, sigla, idlotacao_superior, idfuncionario_responsavel) "
				+ " VALUES (?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			stmt.setString(1, novaLotacao.getNome()); 	
			if (novaLotacao instanceof Diretoria) {
				Diretoria diretoria = (Diretoria) novaLotacao;
				stmt.setString(2, diretoria.getSigla());
			} else {				
				stmt.setString(2, "ger");
			}
			if (novaLotacao instanceof Diretoria) {
				stmt.setInt(3, 0); 		//ok
			} else {
				Gerencia g3 = (Gerencia) novaLotacao;
				stmt.setInt(3, g3.getLotacaoSuperior().getId());
			}
			
			if (novaLotacao.getResponsavel() != null) {
				stmt.setInt(4, novaLotacao.getResponsavel().getId()); 		
			} else {
				stmt.setInt(4, 0);
			}
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				int idGerado = rs.getInt(1);
				novaLotacao.setId(idGerado);
				System.out.println("Lotacao de id " + idGerado + " inserida." );
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir nova Lotacao.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novaLotacao;	
	}

	private void subordinarOperacionais(Gerencia novaGerencia, ArrayList<Operacional> operacionais) {
		// TODO Auto-generated method stub
		
	}

	private void subordinarGerencias(Diretoria novaDiretoria, ArrayList<Gerencia> gerencias) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean excluir(int id) {
		//Apagar lotacaoSuperior de todos as gerencias da Diretoria
		LotacaoDAO lotDAO = new LotacaoDAO();
		lotDAO.desvincularDiretoria(id);
		//Apagar lotacaoSuperior de todos os funcionarios da Gerencia
		lotDAO.desvincularFuncionarios(id);
		
		//Apagar a lotacao
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM LOTACAO WHERE ID= " + id;
		Statement stmt = Banco.getStatement(conn);
		
		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir LOTACAO.");
			System.out.println("Erro: " + e.getMessage());
		}

		return quantidadeLinhasAfetadas > 0;
	}

	private void desvincularFuncionarios(int id) {
		// TODO Auto-generated method stub
		
	}

	private void desvincularDiretoria(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean alterar(Lotacao entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Lotacao consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		String sql = " SELECT id, nome, sigla, idlotacao_superior, idfuncionario_responsavel "
				+ " FROM lotacao "
				+ " WHERE ID=" + id;
		
		Statement stmt = Banco.getStatement(conn); 
		LotacaoDAO lotDAO = new LotacaoDAO();
		Lotacao lotacao= null;
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);
			
			if(resultadoDaConsulta.next()) {
				if (resultadoDaConsulta.getString("sigla").equals("ger")) {					
					lotacao = new Gerencia();
					lotacao = (Gerencia) construirLotacaoDoResultSet(resultadoDaConsulta);
/**
 * 
 * 
 * 						NUNCA ENTRA NESSE IF (?????????)
 * 
 * 
 * 
 * 
 * 
 */


				} else {
					lotacao = new Diretoria();
					lotacao = (Diretoria) construirLotacaoDoResultSet(resultadoDaConsulta);
				}
			}
			
			if(lotacao instanceof Diretoria) {
				Diretoria dir = (Diretoria) lotacao;
				ArrayList<Gerencia> gerencias = lotDAO.consultarTodosPorIdDiretoria(id);
				dir.setGerencias(gerencias);
			}
			
			if(lotacao instanceof Gerencia) {
				FuncionarioDAO funDAO = new FuncionarioDAO();

				Gerencia ger = (Gerencia) lotacao;
				ArrayList<Operacional> funcionarios = funDAO.consultarTodosPorIdGerencia(id);

				System.out.println(funcionarios);
				ger.setOperacionais(funcionarios);

			}

			
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Lotacao por id = " + id);
			System.out.println("Erro: " + e.getMessage());
		}
		
		return lotacao;
	}

	private Lotacao construirLotacaoDoResultSet(ResultSet rs) {
		Lotacao l = null;
		Funcionario f = null;

		try {
			if (rs.getString("sigla").equals("ger")) {
				l = new Gerencia();
				f = new Gerente();
				f.setId(rs.getInt("idfuncionario_responsavel"));
				l.setResponsavel(f);
				Diretoria lsuperior = new Diretoria();
				
				lsuperior.setId(rs.getInt("idlotacao_superior"));
				((Gerencia) l).setLotacaoSuperior(lsuperior);
			} else {
				l = new Diretoria();
				f = new Diretor();
				f.setId(rs.getInt("idfuncionario_responsavel"));
				l.setResponsavel(f);
				((Diretoria) l).setSigla(rs.getString("sigla"));
				((Diretoria) l).setLotacaoSuperior(null);
			}
			
			l.setId(rs.getInt("id"));
			l.setNome(rs.getString("nome"));

			



			
			

		} catch (SQLException e) {
			System.out.println("Erro ao construir lotacao a partir do ResultSet.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return l;
	}

	@Override
	public ArrayList<Lotacao> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Gerencia> consultarTodosPorIdDiretoria(int id) {
		Connection conn = Banco.getConnection();
		String sql = " SELECT id, nome, sigla, idlotacao_superior, idfuncionario_responsavel "
				+ " FROM LOTACAO "
				+ " WHERE idlotacao_superior = " + id;
		
		Statement stmt = Banco.getStatement(conn); 
		ArrayList<Gerencia> lotacoes= new ArrayList<Gerencia>();
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);
			
			while(resultadoDaConsulta.next()) {
				Gerencia lotacao= (Gerencia) construirLotacaoDoResultSet(resultadoDaConsulta);
				lotacoes.add(lotacao);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Gerencias por idDiretoria. idDiretoria: " + id);
			System.out.println("Erro: " + e.getMessage());
		}
		
		return lotacoes;	

	}
	

}
