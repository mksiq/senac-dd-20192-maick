package model.executavel.lista1;

import java.util.ArrayList;

import model.dao.lista1.FuncionarioDAO;
import model.dao.lista1.LotacaoDAO;
import model.entity.lista1.Diretor;
import model.entity.lista1.Diretoria;
import model.entity.lista1.Gerencia;
import model.entity.lista1.Gerente;
import model.entity.lista1.Lotacao;
import model.entity.lista1.Operacional;

public class Executavel {

	public static void main(String[] args) {


		Diretoria d1 = new Diretoria();

		Operacional o1 = new Operacional(0, "Hercules", "00300400506", "M", 33, 2440, null);		
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		funcDAO.salvar(o1);

		
		d1 = construirDiretoria();
		LotacaoDAO dirDAO = new LotacaoDAO();
		dirDAO.salvar(d1);

	}

	private static Diretoria construirDiretoria() {
		
		Diretoria diretoria = new Diretoria();
		diretoria.setNome("Central");
		
		Diretor diretor = new Diretor();
		diretor = construirDiretor();
		diretoria.setResponsavel(diretor);
		diretoria.setSigla("CEO");
		ArrayList<Gerencia> gerencias = contruirGerencias();
		diretoria.setGerencias(gerencias);
		
		
	
		return diretoria;
	}

	private static ArrayList<Gerencia> contruirGerencias() {
		ArrayList<Gerencia> gerencia = new ArrayList<Gerencia>();
		Diretoria d1 = new Diretoria();
		d1.setId(1);
		Gerencia g1 = new Gerencia();
		g1.setNome("Financeiro");
		g1.setLotacaoSuperior(d1);
		Gerente gF1 = criarGerente(1);
		g1.setResponsavel(gF1);
		
		ArrayList<Operacional> operacionais1 = new ArrayList<Operacional>();
		operacionais1 = criarOperacionais(1);
		g1.setOperacionais(operacionais1);
		
		gerencia.add(g1);
		Gerencia g2 = new Gerencia();
		g2.setNome("Marketing");
		g2.setLotacaoSuperior(d1);
		Gerente gF2 = criarGerente(2);
		g2.setResponsavel(gF2);
		
		ArrayList<Operacional> operacionais2 = new ArrayList<Operacional>();
		operacionais2 = criarOperacionais(2);
		g2.setOperacionais(operacionais2);
		
		
		
		return gerencia;
	}

	private static ArrayList<Operacional> criarOperacionais(int i) {
		ArrayList<Operacional> op = new ArrayList<Operacional>();
		if(i == 0) {
			Gerencia g1 = new Gerencia();
			g1.setId(1);
			Operacional o1 = new Operacional(0, "Hercules", "00300400506", "M", 33, 2440, g1);
			Operacional o2 = new Operacional(0, "Medusa", "00400500607", "F", 28, 2567, g1);
			op.add(o1);
			op.add(o2);
		} else {
			Gerencia g2 = new Gerencia();
			g2.setId(2);
			Operacional o3 = new Operacional(0, "Hades", "00500600708", "M", 33, 2540, g2);
			Operacional o4 = new Operacional(0, "Artemis", "00600700809", "F", 34, 2421, g2);
			op.add(o3);
			op.add(o4);
		}
		
		return op;
	}

	private static Gerente criarGerente(int i) {
		Gerente novoGerente = null;
		if (i == 1 ) {
			novoGerente = new Gerente(0, "Gaius Julius Caesar", "00200300456", "M", 66, 7343, null, 2341);
		} else {
			novoGerente = new Gerente(0, "Marcus Aurelius", "00200300456", "M", 55, 2332, null, 1231);
		}
		
		return novoGerente;
	}

	private static Diretor construirDiretor() {
		Diretor diretor = new Diretor(0, "Elon Musk", "00100200345","M", 45, 44340, null, 4949);		
		
		return diretor;
	}

}
