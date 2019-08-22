package model.entity.lista1;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Diretor extends Funcionario {
	private double comissao;
	
	Locale ptBr = new Locale("pt", "BR");

	public void imprimir() {

		System.out.printf("%3s  %-23s  %-3s  %-3s  %-15s %-13s %-13s %-13s %13s %13s\n", this.getId(), this.getNome(), this.getSexo(),
				this.getIdade(), NumberFormat.getCurrencyInstance(ptBr).format(this.getSalarioBruto()), NumberFormat.getCurrencyInstance(ptBr).format(this.getComissao()), NumberFormat.getCurrencyInstance(ptBr).format(this.getDescontoImpostoRenda()), 
				NumberFormat.getCurrencyInstance(ptBr).format(this.getDescontoPrevidencia()), NumberFormat.getCurrencyInstance(ptBr).format(this.getSalarioBase()), NumberFormat.getCurrencyInstance(ptBr).format(this.calcularSalario()));

	}
	
	public Diretor(int id,String nome, String cpf, String sexo, int idade, double salarioBruto, Lotacao lotacao, double comissao) {
		super(id, nome, cpf, sexo, idade, salarioBruto, lotacao);
		this.comissao = comissao;
	}

	public Diretor() {
		super();
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
