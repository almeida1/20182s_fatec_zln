package com.fatec.sce.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class LivroDAO implements ILivroDAO {
	Logger logger = Logger.getLogger(LivroDAO.class);

	public int adiciona(Livro livro) {
		PreparedStatement ps;
		int codigoRetorno = 0;
		try (Connection conn = MySQLDAOFactory.createConnection()) {
			ps = (PreparedStatement) conn.prepareStatement("insert into Livro (isbn, titulo, autor) values(?,?,?)");
			ps.setString(1, livro.getIsbn());
			ps.setString(2, livro.getTitulo());
			ps.setString(3, livro.getAutor());
			codigoRetorno = ps.executeUpdate();
			logger.info("codigo de retorno do metodo adiciona livro = " + codigoRetorno);
			ps.close();
		} catch (SQLException e) {
			logger.info("erro = " + e.getMessage());
			throw new RuntimeException(e);
		}
		return codigoRetorno;
	}

	public int exclui(String isbn) {
		java.sql.PreparedStatement ps;
		int codigoretorno = 0;
		try (Connection conn = MySQLDAOFactory.createConnection()) {
			ps = conn.prepareStatement("delete from Livro where isbn = ?");
			ps.setString(1, isbn);
			codigoretorno = ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("erro = " + e.getMessage());
			throw new RuntimeException(e);
		}
		return codigoretorno;
	}

	public Livro consulta(String isbn) {
		Livro livro = null;
		java.sql.PreparedStatement ps;
		try (Connection conn = MySQLDAOFactory.createConnection()) {
			ps = conn.prepareStatement("select * from Livro where isbn = ?");
			ps.setString(1, isbn);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				livro = new Livro();
				livro.setIsbn(resultSet.getString("isbn"));
				livro.setTitulo(resultSet.getString("titulo"));
				livro.setAutor(resultSet.getString("autor"));
			}
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			logger.info("erro metodo consulta livro = " + e.getMessage());
		} catch (Exception e) {
			logger.info("erro metodo consulta livro = " + e.getMessage());
		}
		return livro;
	}
}