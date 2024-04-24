package br.com.frateu.blogcomentario.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {
	Dotenv dotenv = Dotenv.configure().filename("blog.env").load();

	Connection connection = null;

	public DatabaseConnection() {
		super();

		String dbUrl      = dotenv.get("DATABASE_URL");
		String dbUser     = dotenv.get("DATABASE_USER");
		String dbPassword = dotenv.get("DATABASE_PASSWORD");

		try {
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			System.out.println("Conexão com o banco de dados bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Falha na conexão com o banco de dados:");

			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Falha no encerramento da conexão com o banco de dados:");

			e.printStackTrace();
		}
	}

	public Statement createStatement() {
		Statement statement = null;

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return statement;
	}

	public PreparedStatement createPreparedStatement(String pSQL) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(pSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return preparedStatement;
	}
}
