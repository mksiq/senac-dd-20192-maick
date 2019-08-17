package model.dao.lista1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.TelefoneDAO;
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
		String sql = " INSERT INTO LOCACAO(ID, NOME, , CPF, IDENDERECO) "
				+ " VALUES (?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			stmt.setString(2, novaLotacao.getNome()); 		//ok
			if (novaLotacao instanceof Diretoria) {
				stmt.setInt(3, 0);
				Diretoria novaDiretoria = (Diretoria) novaLotacao;
				stmt.setString(5, novaDiretoria.getSigla());
				lotDAO.subordinarGerencias(novaDiretoria, novaDiretoria.getGerencias());
				
			} else {
				Gerencia novaGerencia = (Gerencia) novaLotacao;
				stmt.setInt(3, novaLotacao.getLotacaoSuperior().getId());
				lotDAO.subordinarOperacionais(novaGerencia, novaGerencia.getOperacionais());
			} 												//ok
			stmt.setInt(4, novaLotacao.getResponsavel().getId());
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				int idGerado = rs.getInt(1);
				novaLotacao.setId(idGerado);
			}
			

		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo Funcionário.");
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
		// TODO Auto-generated method stub
		return false;
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
