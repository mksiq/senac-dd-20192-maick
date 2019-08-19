package model.dao.lista1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.lista1.Banco;
import model.entity.lista1.Diretoria;
import model.entity.lista1.Funcionario;
import model.entity.lista1.Gerencia;
import model.entity.lista1.Lotacao;
import model.entity.lista1.Operacional;

public class LotacaoDAO implements BaseDAO<Lotacao> {

	@Override
	public Lotacao salvar(Lotacao novaLotacao) {
		LotacaoDAO lotDAO = new LotacaoDAO();
		
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO LOTACAO(nome, sigla, idlotacao_superior, idfuncionario_responsavel) "
				+ " VALUES (?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			stmt.setString(1, novaLotacao.getNome()); 		//ok
			if (novaLotacao instanceof Diretoria) {
				stmt.setInt(2, 0);
				Diretoria novaDiretoria = (Diretoria) novaLotacao;
				stmt.setString(4, novaDiretoria.getSigla());
				lotDAO.subordinarGerencias(novaDiretoria, novaDiretoria.getGerencias());
				
			} else {
				Gerencia novaGerencia = (Gerencia) novaLotacao;
				stmt.setInt(2, novaLotacao.getLotacaoSuperior().getId());
				lotDAO.subordinarOperacionais(novaGerencia, novaGerencia.getOperacionais());
			} 												//ok
			stmt.setInt(3, novaLotacao.getResponsavel().getId());
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				int idGerado = rs.getInt(1);
				novaLotacao.setId(idGerado);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Lotacao> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}