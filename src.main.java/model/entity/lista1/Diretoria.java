package model.entity.lista1;

import java.text.NumberFormat;
import java.util.ArrayList;

import model.dao.lista1.FuncionarioDAO;

public class Diretoria extends Lotacao{
	private String sigla;
	private ArrayList<Gerencia> gerencias;

	
	public Diretoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Diretoria(int id, String nome, Lotacao lotacaoSuperior, Funcionario responsavel, String sigla,  ArrayList<Gerencia> gerencias) {
		super(id, nome, lotacaoSuperior, responsavel);
		this.sigla = sigla;
		this.gerencias = gerencias;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public ArrayList<Gerencia> getGerencias() {
		return gerencias;
	}
	public void setGerencias(ArrayList<Gerencia> gerencias) {
		this.gerencias = gerencias;
	}
	
	public void imprimir() {
		Diretor diretor = new Diretor();
		diretor = (Diretor) fDAO.consultarPorId(this.getResponsavel().getId());
		this.setResponsavel(diretor);
		System.out.printf("%3s  %-30s %-10s  %-20s %-10s\n", this.getId(),
				this.getNome() ,  this.getSigla(),
				this.getResponsavel().getNome(), this.getResponsavel().getId());
	}





	



	
	
	
}
