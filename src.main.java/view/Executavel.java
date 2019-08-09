package view;

import java.util.ArrayList;
import java.util.Random;

import model.dao.EnderecoDAO;
import model.dao.TelefoneDAO;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Telefone;



public class Executavel {

	private static final String CODIGO_PAIS_BRASIL = "55";

	public static void main(String[] args) {
		//exerc√≠cio 1
		//criarClientesMostrarNoConsole();

		//M√©todos de testes da aula 2
		
		//salvarTelefonesAleatorios();
		//atualizarTelefone();
		//excluirTelefone();
		//consultarTelefones();
		
		salvarNovoEnderecoNoBanco();
		excluirEndereco();
		atualizarEndereco();
		
		consultarEnderecos();
	}

	private static void excluirEndereco() {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		if(enderecoDAO.excluir(3)) {
			System.out.println("Excluiu");
		}else {
			System.out.println("N„o excluiu");
		}
		
	}

	private static void atualizarEndereco() {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco enderecoQueSeraAtualizado = new Endereco(2, "das Atualizacoes", "88000000", "SC", "Palhoca", "Passa Vinte", "Casa");
		if(enderecoDAO.alterar(enderecoQueSeraAtualizado)) {
			System.out.println("Alterou");
		}else {
			System.out.println("N„o alterou");
		} 
	}

	private static void consultarEnderecos() {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		System.out.println(enderecoDAO.consultarPorId(1));
		System.out.println("");
		System.out.println("************* Todos os enderecos *************");
		ArrayList<Endereco> todosOsEnderecos = enderecoDAO.consultarTodos();
		
		for(Endereco e: todosOsEnderecos) {
			System.out.println(e);
		}
		System.out.println("");
		System.out.println("**********************************************");
		
	}

	private static void consultarTelefones() {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		System.out.println(telefoneDAO.consultarPorId(2));
		System.out.println("");
		System.out.println("************* Todos os telefones *************");
		ArrayList<Telefone> todosOsTelefones = telefoneDAO.consultarTodos();
		
		for(Telefone t: todosOsTelefones) {
			System.out.println(t);
		}
		System.out.println("");
		System.out.println("**********************************************");
	}

	private static void atualizarTelefone() {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		Telefone telefoneQueSeraAtualizado = new Telefone(2, 0, "88", "66", "1232-3211", "MÛvel", false);
		if(telefoneDAO.alterar(telefoneQueSeraAtualizado)) {
			System.out.println("Alterou");
		}else {
			System.out.println("N„o alterou");
		} 
	}

	private static void excluirTelefone() {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		if(telefoneDAO.excluir(3)) {
			System.out.println("Excluiu");
		}else {
			System.out.println("N„o excluiu");
		}
	}

	private static void salvarTelefonesAleatorios() {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		ArrayList<Telefone> telefones = criarTelefones();

		for(Telefone t: telefones) {
			telefoneDAO.salvar(t);
		}
	}

	private static void criarClientesMostrarNoConsole() {
		Endereco endereco1 = new Endereco("Mauro Ramos", "88000-123", 
				"SC", "FlorianÛpolis", "Centro", "10A");

		//Constru√ß„o da lista de telefones
		ArrayList<Telefone> telefonesCliente1 = new ArrayList<Telefone>();
		Telefone tel1 = new Telefone(0, 0, "55", "048", "2020-5555", "Fixa", true);
		telefonesCliente1.add(tel1);

		//Listas de telefones s„o criadas por um m√©todo auxiliar criarTelefones() criado na classe Executavel
		Cliente cliente1 = new Cliente("Edson", "Arantes do Nascimento", "010.010.100-10", telefonesCliente1, endereco1);
		Cliente cliente2 = new Cliente("Artur", "Antunes Coimbra", "000.000.100-10", criarTelefones(), endereco1);
		Cliente cliente3 = new Cliente("Manoel", "dos Santos", "777.010.100-10", criarTelefones(), endereco1);
		Cliente cliente4 = new Cliente("Roberto", "Rivellino", "011.010.100-10",  criarTelefones(), endereco1);
		Cliente cliente5 = new Cliente("Eduardo", "Gon√ßalves de Andrade", "009.010.100-10", criarTelefones(), endereco1);

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);

		for (Cliente c : clientes) {
			System.out.println("************************************************************************************");
			System.out.println(c);
		}
		System.out.println("************************************************************************************");

		//TODO exerc√≠cio 2 -> salvar os clientes no banco
	}

	private static void salvarNovoEnderecoNoBanco() {
		Endereco novoEndereco = new Endereco("Mauro Ramos", "88000123", 
				"SC", "FlorianÛpolis", "Centro", "10A");

		EnderecoDAO dao = new EnderecoDAO();
		novoEndereco = dao.salvar(novoEndereco);

		if(novoEndereco.getId() > 0) {
			System.out.println("Endereco salvo com sucesso.");
		}else {
			System.out.println("Endereco nao foi salvo.");
		}
/**		a ser atualizado */
		Endereco novoEndereco2 = new Endereco("das Palmeiras", "88130000", 
				"SC", "Palhoca", "Passa Vinte", "Casa");

		novoEndereco = dao.salvar(novoEndereco2);

		if(novoEndereco.getId() > 0) {
			System.out.println("Endereco salvo com sucesso.");
		}else {
			System.out.println("Endereco nao foi salvo.");
		}
/**		a ser excluido */
		Endereco novoEndereco3 = new Endereco("a ser excluido", "88160000", 
				"SC", "Biguacu", "Passa Vinte", "Casa");

		novoEndereco = dao.salvar(novoEndereco3);

		if(novoEndereco.getId() > 0) {
			System.out.println("Endereco salvo com sucesso.");
		}else {
			System.out.println("Endereco nao foi salvo.");
		}
	}

	/**
	 * Cria uma lista de telefones rand√¥micos.
	 * @return uma lista de telefones.
	 */
	private static ArrayList<Telefone> criarTelefones() {
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(new Telefone(0, 0, CODIGO_PAIS_BRASIL, criarDdd(), criarNumeroTelefone(), "Fixa", true));
		telefones.add(new Telefone(0, 0, CODIGO_PAIS_BRASIL, criarDdd(), criarNumeroTelefone(), "Fixa", true));
		telefones.add(new Telefone(0, 0, CODIGO_PAIS_BRASIL, criarDdd(), criarNumeroTelefone(), "MÛvel", true));

		return telefones;
	}

	/**
	 * Cria um DDD rand√¥mico
	 * 
	 * @return uma String com o DDD, com 2 caracteres.
	 */
	private static String criarDdd() {
		Random ran = new Random();
		return "" + ran.nextInt(10) + ran.nextInt(10);
	}

	/**
	 * Cria um n√∫mero de telefone rand√¥mico, no formato 
	 * ####-####, onde # √© um n√∫mero de 0 a 9
	 * 
	 * @return o n√∫mero gerado.
	 */
	private static String criarNumeroTelefone() {
		Random ran = new Random();
		String prefixo = "" + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10);
		String ramal = "" + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10);

		return prefixo + "-" + ramal;
	}
}