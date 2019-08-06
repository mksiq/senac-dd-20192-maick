package view;

import java.util.ArrayList;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Telefone;

public class executavel {
	public static void main(String args[]) {
		
		Endereco e1 = new Endereco(1, "Rua das Figueiras", "88100-000", "Santa Catarina", "Florianopolis", "100", "Centro" );
		Endereco e2 = new Endereco(2, "Rua das Palmeiras", "88160-000", "Santa Catarina", "Biguacu", "101", "Centro" );
		Endereco e3 = new Endereco(3, "Rua das Laranjeiras", "88130-000", "Santa Catarina", "Palhoca", "102", "Pedra Branca" );
		Endereco e4 = new Endereco(4, "Rua das Macieiras", "88110-000", "Santa Catarina", "Sao Jose", "103", "Kobrasol" );
		
		
		ArrayList<Telefone> listaT1 = criarTelefone();
		
		Cliente c1 = new Cliente(1,"Septimio", "Severo", "000.000.000-01", listaT1, e1);
		Cliente c2 = new Cliente(2,"Otavio", "Augusto", "000.000.000-02", listaT1, e2);
		Cliente c3 = new Cliente(3,"Julio", "Cesar", "000.000.000-03", listaT1, e3);
		Cliente c4 = new Cliente(4,"Valerio", "Valente", "000.000.000-04", listaT1, e4);
		Cliente c5 = new Cliente(5,"Flavio", "Teodosio", "000.000.000-05", listaT1, e4);
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		clientes.add(c4);
		clientes.add(c5);
		
		for (Cliente c : clientes) {
			System.out.println("**************************************************************************");
			System.out.println(c);			
		}
		System.out.println("**************************************************************************");
		
		
	}

	
	private static ArrayList<Telefone> criarTelefone(){
		Telefone t1 = new Telefone(1, "+55","048","99991-0001","Móvel",true, 0);
		
		Telefone t2 = new Telefone(1, "+55","048","3339-0000","Fixo",false, 0);
		Telefone t3 = new Telefone(1, "+55","051","3342-0000","Fixo",false, 0);
		Telefone t4 = new Telefone(1, "+55","048","3341-0000","Fixo",true, 0);
		Telefone t5 = new Telefone(1, "+55","048","99991-0002","Móvel",true, 0);
		Telefone t6 = new Telefone(1, "+55","048","99991-0000","Fixo",true, 0);
		Telefone t7 = new Telefone(1, "+55","055","99995-0003","Móvel",true, 0);
		Telefone t8 = new Telefone(1, "+55","055","99991-0004","Móvel",true, 0);
		
		ArrayList<Telefone> listaT1 = new ArrayList<Telefone>();
		listaT1.add(t1);
		listaT1.add(t4);
		ArrayList<Telefone> listaT2 = new ArrayList<Telefone>();
		listaT2.add(t5);		
		ArrayList<Telefone> listaT3 = new ArrayList<Telefone>();
		listaT3.add(t6);
		ArrayList<Telefone> listaT4 = new ArrayList<Telefone>();
		listaT4.add(t7);
		ArrayList<Telefone> listaT5 = new ArrayList<Telefone>();
		listaT5.add(t8);
		
		return listaT1;
	}
}
