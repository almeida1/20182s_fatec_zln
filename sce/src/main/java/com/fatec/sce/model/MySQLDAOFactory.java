package com.fatec.sce.model;

import com.mysql.jdbc.Connection;

public class MySQLDAOFactory extends DAOFactory {
	public static Connection createConnection() {
		return (Connection) new FabricaDeConexoes().getConnection();
	}

	@Override
	public ILivroDAO getLivroDAO() {
		return new LivroDAO();
	}
}