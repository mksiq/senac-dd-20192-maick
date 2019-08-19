package model.entity.lista1;

import java.util.ArrayList;

public class Diretor extends Funcionario {
	private double comissao;

	
	
	public Diretor(int id,String nome, String cpf, String sexo, int idade, double salarioBruto, Lotacao lotacao, double comissao) {
		super(id, nome, cpf, sexo, idade, salarioBruto, lotacao);
		this.comissao = comissao;
	}
	
	

	public Diretor() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public double calcularSalario() {
		return this.getSalarioBase() * 3 + this.comissao;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
	
	
}