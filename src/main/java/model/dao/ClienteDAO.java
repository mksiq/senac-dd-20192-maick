package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Cliente;

public class ClienteDAO implements BaseDAO<Cliente>{

	@Override
	public Cliente salvar(Cliente novaEntidade) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean alterar(Cliente entidade) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public Cliente consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cliente> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}


}
