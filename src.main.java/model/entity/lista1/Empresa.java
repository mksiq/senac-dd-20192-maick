package model.entity.lista1;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import model.dao.lista1.FuncionarioDAO;

public class Empresa {

	ArrayList<Diretoria> diretorias = new ArrayList<Diretoria>();
	Locale ptBr = new Locale("pt", "BR");
	
	
	public ArrayList<Diretoria> getDiretorias() {
		return diretorias;
	}



	public void setDiretorias(ArrayList<Diretoria> diretorias) {
		this.diretorias = diretorias;
	}



	public void imprimir() {
		
		int totalF = 0;
		double totalSalarioBruto = 0;
		double totalSalarioBase = 0;
		double totalComissao = 0;
		double totalImpRenda = 0;
		double totalINSS = 0;
		System.out.printf("%-108s \n", "-----------------------------------------------------| Empresa |-----------------------------------------------------");
		System.out.printf("%3s  %-30s %-10s %-20s %-10s\n", "ID" , "NOME DIRETORIA" , "SIGLA", "RESPONSÁVEL", "ID RESP");
		for (int i = 0; i < diretorias.size(); i++) {
			this.diretorias.get(i).imprimir();
			System.out.println();
			System.out.printf("%3s  %-20s  %-3s  %-3s  %-13s %-13s %-13s %-13s %-13s %-13s\n", "ID", "NOME DIRETOR", "SEXO",
					"IDADE", "SAL BRUTO", "COMISSAO", "IMP RENDA" ,"INSS", "SAL BASE","SAL LIQUIDO");
			this.diretorias.get(i).getResponsavel().imprimir();
			System.out.printf("\n %3s  %-30s %-10s %-20s %-10s\n", "ID" , "NOME GERENCIA" , " ", "RESPONSÁVEL", "ID RESP");
			totalF++;
			
			totalSalarioBruto += this.diretorias.get(i).getResponsavel().getSalarioBruto();
			totalSalarioBase += this.diretorias.get(i).getResponsavel().getSalarioBase();
			Diretor diretor = (Diretor) this.diretorias.get(i).getResponsavel();
			totalComissao += diretor.getComissao();
			totalImpRenda += this.diretorias.get(i).getResponsavel().getDescontoImpostoRenda();
			totalINSS +=  this.diretorias.get(i).getResponsavel().getDescontoPrevidencia();
			
			for(int j = 0; j <  this.getDiretorias().get(i).getGerencias().size(); j++) {
				this.diretorias.get(i).getGerencias().get(j).imprimir();
				System.out.printf("%3s  %-20s  %-3s  %-3s  %-13s %-13s %-13s %-13s %13s %13s\n", "ID", "NOME GERENTE", "SEXO",
						"IDADE", "SAL BRUTO", "COMISSAO", "IMP RENDA" ,"INSS", "SAL BASE", "SAL LIQUIDO");
				this.diretorias.get(i).getGerencias().get(j).getResponsavel().imprimir();
				System.out.println();
				totalF++;
				totalSalarioBruto += this.diretorias.get(i).getGerencias().get(j).getResponsavel().getSalarioBruto();
				totalSalarioBase += this.diretorias.get(i).getGerencias().get(j).getResponsavel().getSalarioBase();
				Gerente gerente = (Gerente) this.diretorias.get(i).getGerencias().get(j).getResponsavel();
				totalComissao += gerente.getComissao();
				totalImpRenda += this.diretorias.get(i).getGerencias().get(j).getResponsavel().getDescontoImpostoRenda();
				totalINSS +=  this.diretorias.get(i).getGerencias().get(j).getResponsavel().getDescontoPrevidencia();
				
				//gambitech
				FuncionarioDAO fDAO = new FuncionarioDAO();
				ArrayList<Operacional> operacionais = fDAO.consultarTodosPorIdGerencia(this.diretorias.get(i).getGerencias().get(j).getId());
				this.diretorias.get(i).getGerencias().get(j).setOperacionais(operacionais);
				
				System.out.printf("%3s  %-20s  %-3s  %-3s  %-13s %-13s %-13s %-13s %-13s %-13s\n", "ID", "NOME OPERACIONAL", "SEXO",
						"IDADE", "SAL BRUTO", " ", "IMP RENDA" ,"INSS", "SAL BASE", "SAL LIQUIDO");
				for (int k = 0; k < this.getDiretorias().get(i).getGerencias().get(j).getOperacionais().size(); k++) {
					this.diretorias.get(i).getGerencias().get(j).getOperacionais().get(k).imprimir();
					totalF++;
					totalSalarioBruto += this.diretorias.get(i).getGerencias().get(j).getOperacionais().get(k).getSalarioBruto();
					totalSalarioBase += this.diretorias.get(i).getGerencias().get(j).getOperacionais().get(k).getSalarioBase();
					totalImpRenda += this.diretorias.get(i).getGerencias().get(j).getOperacionais().get(k).getDescontoImpostoRenda();
					totalINSS +=  this.diretorias.get(i).getGerencias().get(j).getOperacionais().get(k).getDescontoPrevidencia();
					System.out.println();
				}
			}
			System.out.println();
		}
		System.out.printf("%-108s \n", "---------------------------------------------------| Relatório |---------------------------------------------------");
		System.out.printf("%5s  %-15s  %-3s  %-3s  %-13s %-13s %-13s %-13s %13s %-20s\n", "TOTAL", "  ", " ",
				" ", "SAL BRUTO", "COMISSAO", "IMP RENDA" ,"INSS", "SAL LIQUIDO", " ");
		System.out.printf("%5s  %-15s  %-3s  %-3s  %-13s %-13s %-13s %-13s %13s %-20s\n", totalF, "  ", " ",
				" ", NumberFormat.getCurrencyInstance(ptBr).format(totalSalarioBruto), NumberFormat.getCurrencyInstance(ptBr).format(totalComissao), NumberFormat.getCurrencyInstance(ptBr).format(totalImpRenda) ,NumberFormat.getCurrencyInstance(ptBr).format(totalINSS), NumberFormat.getCurrencyInstance(ptBr).format(totalSalarioBase), " ");

	}
	
}
