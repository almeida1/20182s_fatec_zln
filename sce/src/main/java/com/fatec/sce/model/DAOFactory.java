package com.fatec.sce.model;

//Abstract class DAO Factory
public abstract class DAOFactory {
	// List of DAO types supported by the factory
	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	public static final int SQLSERVER = 3;

	public abstract ILivroDAO getLivroDAO();

	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL:
			return new MySQLDAOFactory();
		// case ORACLE: return new OracleDAOFactory();
		// case SQLSERVER: return new SQLServerDAOFactory();
		default:
			return null;
		}
	}
}