package model.entity.lista1;

import java.text.NumberFormat;
import java.util.ArrayList;

import model.dao.lista1.FuncionarioDAO;

public class Empresa {

	ArrayList<Diretoria> diretorias = new ArrayList<Diretoria>();
	
	
	
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
		System.out.printf("%-108s \n", "-----------------------------------------------------| Empresa |-----------------------------------------------------");
		System.out.printf("%3s  %-30s %-10s %-20s %-10s\n", "ID" , "NOME DIRETORIA" , "SIGLA", "RESPONSÁVEL", "ID RESP");
		for (int i = 0; i < diretorias.size(); i++) {
			this.diretorias.get(i).imprimir();
			System.out.println();
			System.out.printf("%3s  %-20s  %-3s  %-3s  %-13s %-13s %-13s %-13s %13s %-20s\n", "ID", "NOME DIRETOR", "SEXO",
					"IDADE", "SAL BRUTO", "COMISSAO", "IMP RENDA" ,"INSS", "SAL LIQUIDO", "LOCACAO");
			this.diretorias.get(i).getResponsavel().imprimir();
			System.out.printf("\n %3s  %-30s %-10s %-20s %-10s\n", "ID" , "NOME FINANCEIRO" , " ", "RESPONSÁVEL", "ID RESP");
			totalF++;
			
			totalSalarioBruto += this.diretorias.get(i).getResponsavel().getSalarioBruto();
			totalSalarioBase += this.diretorias.get(i).getResponsavel().getSalarioBase();
			for(int j = 0; j <  this.getDiretorias().get(i).getGerencias().size(); j++) {
				this.diretorias.get(i).getGerencias().get(j).imprimir();
				System.out.printf("%3s  %-20s  %-3s  %-3s  %-13s %-13s %-13s %-13s %13s %-20s\n", "ID", "NOME GERENTE", "SEXO",
						"IDADE", "SAL BRUTO", "COMISSAO", "IMP RENDA" ,"INSS", "SAL LIQUIDO", "LOCACAO");
				this.diretorias.get(i).getGerencias().get(j).getResponsavel().imprimir();
				System.out.println();
				totalF++;
				totalSalarioBruto += this.diretorias.get(i).getGerencias().get(j).getResponsavel().getSalarioBruto();
				totalSalarioBase += this.diretorias.get(i).getGerencias().get(j).getResponsavel().getSalarioBase();
				FuncionarioDAO fDAO = new FuncionarioDAO();
				ArrayList<Operacional> operacionais = fDAO.consultarTodosPorIdGerencia(this.diretorias.get(i).getGerencias().get(j).getId());
				this.diretorias.get(i).getGerencias().get(j).setOperacionais(operacionais);
				
				System.out.printf("%3s  %-20s  %-3s  %-3s  %-13s %-13s %-13s %-13s %13s %-20s\n", "ID", "NOME OPERACIONAL", "SEXO",
						"IDADE", "SAL BRUTO", " ", "IMP RENDA" ,"INSS", "SAL LIQUIDO", "LOCACAO");
				for (int k = 0; k < this.getDiretorias().get(i).getGerencias().get(j).getOperacionais().size(); k++) {
					this.diretorias.get(i).getGerencias().get(j).getOperacionais().get(k).imprimir();
					totalF++;
					totalSalarioBruto += this.diretorias.get(i).getGerencias().get(j).getOperacionais().get(k).getSalarioBruto();
					totalSalarioBase += this.diretorias.get(i).getGerencias().get(j).getOperacionais().get(k).getSalarioBase();
					System.out.println();
				}
			}
			System.out.println();
		}
		System.out.printf("%-108s \n", "---------------------------------------------------| Relatório |---------------------------------------------------");
		System.out.printf("%5s  %-15s  %-3s  %-3s  %-13s %-13s %-13s %-13s %13s %-20s\n", "TOTAL", "  ", " ",
				" ", "SAL BRUTO", "COMISSAO", "IMP RENDA" ,"INSS", "SAL LIQUIDO", " ");
		System.out.printf("%5s  %-15s  %-3s  %-3s  %-13s %-13s %-13s %-13s %13s %-20s\n", totalF, "  ", " ",
				" ", totalSalarioBruto, "COMISSAO", "IMP RENDA" ,"INSS", totalSalarioBase, " ");

	}
	
}
