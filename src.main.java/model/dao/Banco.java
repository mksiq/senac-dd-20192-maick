package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe criada na disciplina de POO (2019/1).
 * 
 * Representa um banco de dados que se conecta a  aplicaçao
 * 
 * Encapsula metodos da API JDBC, com a definiçao, criaçao e fechamento de
 * conexões a  bancos de dados relacionais
 * 
 * @author Adriano de Melo
 * 
 *         Vilmar Cesar Pereira Júnior (continuaçao em Desenvolvimento Desktop
 *         2018/2) -- ALTERADO remotamente (no github)
 * 
 *         Diferenças entre Statement e PreparedStatement:
 * 
 *         A maioria dos bancos de dados relacionais lida com uma consulta
 *         (query) JDBC / SQL em quatro passos:
 *
 *         1- Interpretar (parse) a consulta SQL;
 * 
 *         2- Compilar a consulta SQL;
 * 
 *         3- Planejar e otimizar o caminho de busca dos dados;
 * 
 *         4- Executar a consulta otimizada, buscando e retornando os dados.
 * 
 *         Um Statement ira sempre passar pelos quatro passos acima para cada
 *         consulta SQL enviada para o banco. Ja um Prepared Statement
 *         pre-executa os passos (1) a (3).
 * 
 *         Entao, ao criar um Prepared Statement alguma pre-otimizaçao e feita
 *         de imediato. O efeito disso e que, se voce pretende executar a mesma
 *         consulta repetidas vezes mudando apenas os parametros de cada uma, a
 *         execuçao usando Prepared Statements sera mais rapida e com menos
 *         carga sobre o banco.
 * 
 *         Outra vantagem dos Prepared Statements e que, se utilizados
 *         corretamente, ajudam a evitar <b>ataques de Injeçao de SQL</b>.
 * 
 *         Note que para isso e preciso que os parametros da consulta sejam
 *         atribuidos atraves dos metodos setInt(), setString(), etc. presentes
 *         na interface PreparedStatement e nao por concatenaçao de strings.
 * 
 *         Para uma consulta que vai ser executada poucas vezes e nao requer
 *         nenhum parametro, Statement basta. Para os demais casos, prefira
 *         PreparedStatement.
 * 
 *         FONTE:
 *         {@link https://pt.stackoverflow.com/questions/99620/qual-a-diferen%C3%A7a-entre-o-statement-e-o-preparedstatement}
 *         ======= Classe responsavel pela conexao JDBC com o banco de dados
 *         escolhido.
 * 
 * @author Adriano de Melo
 *
 */
public class Banco {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String BANCODADOS = "exemplos";
	private static final String CONEXAO = "jdbc:mysql://localhost:3306/" + BANCODADOS
			+ "?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static final int CODIGO_RETORNO_ERRO_EXCLUSAO = 0;
	public static final int CODIGO_RETORNO_SUCESSO_EXCLUSAO = 1;

	/**
	 * Estabelece a conexao JBDC considerando as configurações da classe Banco.
	 * 
	 * @return Connection um objeto de conexao JDBC.
	 * 
	 * @throws ClassNotFoundException caso o nome completo de DRIVER_MYSQL esteja
	 *                                incorreto ou o driver JDBC do banco
	 *                                selecionado nao foi adicionado ao projeto (via
	 *                                .jar ou dependencia no pom.xml).
	 * 
	 * @throws SQLException           caso a URL_CONEXAO, USUARIO e/ou SENHA estejam
	 *                                incorretos.
	 */
	public static Connection getConnection() {
		try {
			Connection conn = null;
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONEXAO, USER, PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Classe do Driver nao foi encontrada. Causa: " + e.getMessage());
			return null;
		} catch (SQLException e) {
			System.out.println("Erro ao obter a Connection. Causa: " + e.getMessage());
			return null;
		}
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento da conexao. Causa: " + e.getMessage());
		}
	}

	/**
	 * 
	 * Solicita um objeto Statement para uma conexao. Este objeto serve para
	 * executar as operações SQL.
	 * 
	 * Este metodo deve ser sempre chamado nos DAOs apos a criaçao da expressao SQL,
	 * geralmente com os metodos execute(sql), executeUpdate(sql) ou
	 * executeQuery(sql), onde "sql" e do tipo String.
	 * 
	 * @param conn uma conexao anteriormente criada.
	 * @return stmt um objeto do tipo Statement
	 * 
	 * @throws SQLException
	 * 
	 */
	public static Statement getStatement(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			return stmt;
		} catch (SQLException e) {
			System.out.println("Erro ao obter o Statement. Causa: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * Fecha um objeto Statement anteriormente criado.
	 * 
	 * Este metodo deve ser sempre chamado nos DAOs apos a execuçao da expressao
	 * SQL.
	 * 
	 * @param stmt um objeto do tipo Statement
	 * 
	 * @throws SQLException
	 * 
	 */
	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do Statement. Causa: " + e.getMessage());
		}
	}

	/**
	 * 
	 * Solicita um objeto PreparedStatement para uma conexao. Este objeto serve para
	 * executar as operações SQL.
	 * 
	 * @param conn uma conexao anteriormente criada.
	 * @return stmt um objeto do tipo PreparedStatement
	 * 
	 * @throws SQLException
	 * 
	 */
	public static PreparedStatement getPreparedStatement(Connection conn) {
		try {
			PreparedStatement stmt = null;
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement. Causa: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * Solicita um objeto PreparedStatement para uma conexao. Este objeto serve para
	 * executar as operações SQL.
	 * 
	 * @param conn uma conexao anteriormente criada.
	 * @return stmt um objeto do tipo PreparedStatement
	 * 
	 * @throws SQLException
	 * 
	 */
	public static PreparedStatement getPreparedStatement(Connection conn, String sql) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement.");
			return null;
		}
	}

	public static PreparedStatement getPreparedStatement(Connection conn, String sql, int tipoRetorno) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql, tipoRetorno);
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement.");
			return null;
		}
	}

	/**
	 * 
	 * Fecha um objeto PreparedStatement anteriormente criado.
	 * 
	 * Este metodo deve ser sempre chamado nos DAOs apos a execuçao da expressao
	 * SQL.
	 * 
	 * @param stmt um objeto do tipo PreparedStatement
	 * 
	 * @throws SQLException
	 * 
	 */
	public static void closePreparedStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do PreparedStatement. Causa: " + e.getMessage());
		}
	}

	/**
	 * 
	 * Fecha um objeto ResultSet anteriormente criado.
	 * 
	 * Este metodo deve ser sempre chamado nos DAOs apos a consulta de todos os
	 * resultados e conversao para objetos.
	 * 
	 * @param result um objeto do tipo ResultSet
	 * 
	 * @throws SQLException
	 * 
	 */
	public static void closeResultSet(ResultSet result) {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do ResultSet. Causa: " + e.getMessage());
		}
	}
}
