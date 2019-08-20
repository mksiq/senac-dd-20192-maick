package model.entity.lista1;

import java.util.ArrayList;

public class Gerencia extends Lotacao{
	private ArrayList<Operacional> operacionais;
	public Gerencia() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Gerencia(int id, String nome, Lotacao lotacaoSuperior, Funcionario responsavel, ArrayList<Operacional> operacionais) {
		super(id, nome, lotacaoSuperior, responsavel);
		this.operacionais = operacionais;
	}

	public ArrayList<Operacional> getOperacionais() {
		return operacionais;
	}
	public void setOperacionais(ArrayList<Operacional> operacionais) {
		this.operacionais = operacionais;
	}
	@Override
	public String toString() {
		return "Gerencia [operacionais=" + operacionais + "]";
	}
	
	public void imprimir() {

		Gerente gerente = new Gerente();
		gerente = (Gerente) fDAO.consultarPorId(this.getResponsavel().getId());
		this.setResponsavel(gerente);


		System.out.printf("%3s  %-30s %-10s  %-20s %-10s\n", this.getId(),
				this.getNome() ,  "",
				this.getResponsavel().getNome(), this.getResponsavel().getId());
		


	}
	
	
	
	
}
