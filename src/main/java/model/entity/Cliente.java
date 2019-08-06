package model.entity;

import java.util.ArrayList;


public class Cliente {
	private int idCliente;
	private String nome;
	private String sobrenome;
	private String cpf;
	private ArrayList<Telefone> telefones;
	private Endereco endereco;
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(int idCliente, String nome, String sobrenome, String cpf, ArrayList<Telefone> telefones,
			Endereco endereco) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.telefones = telefones;
		this.endereco = endereco;
	}
 
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getidCliente() {
		return idCliente;
	}

	public void setidCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ArrayList<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		String mensagem = "Cliente " + this.getNomeCompleto() + "; cpf: " + cpf + "; endereço: "
				+ endereco + "; telefones: \n";
		for (Telefone t : telefones) {
			mensagem += t.toString() + "\n";
		}
	
		return mensagem;
	}
	public String getNomeCompleto() {
		String nomeCompleto = this.getNome()+" "+this.getSobrenome();
		return nomeCompleto;		
	}
}
