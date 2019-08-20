package model.entity.lista1;

import java.text.NumberFormat;
import java.util.Locale;

public class Operacional extends Funcionario{
	Locale ptBr = new Locale("pt", "BR");
	public Operacional() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void imprimir() {
		System.out.printf("%3s  %-20s  %3s  %3s  %-13s %-13s %-13s %-13s %13s", this.getId(), this.getNome(), this.getSexo(),
				this.getIdade(), NumberFormat.getCurrencyInstance(ptBr).format(this.getSalarioBruto()), NumberFormat.getCurrencyInstance(ptBr).format(this.getDescontoImpostoRenda()), 
				NumberFormat.getCurrencyInstance(ptBr).format(this.getDescontoPrevidencia())," ", NumberFormat.getCurrencyInstance(ptBr).format(this.getSalarioBase()));

	}
	public Operacional(int id,String nome, String cpf, String sexo, int idade, double salarioBruto, Lotacao lotacao) {
		super(id, nome, cpf, sexo, idade, salarioBruto, lotacao);

	}

	@Override
	public double calcularSalario() {
		return this.getSalarioBase() * 0.85;
	}
	
	
	
	

}
