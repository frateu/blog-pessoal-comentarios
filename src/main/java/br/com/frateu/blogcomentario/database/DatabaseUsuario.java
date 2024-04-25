package br.com.frateu.blogcomentario.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.frateu.blogcomentario.model.Usuarios;

public class DatabaseUsuario {

    public Usuarios consultarUsuario(Usuarios pUsuario) {
        DatabaseConnection dbConnection = new DatabaseConnection();

        Usuarios usuario = null;

        PreparedStatement statement;

        Integer queryIndex = 0;

        String conectorAND = "";

        try {
            // Executa a consulta
            String query = "SELECT * FROM blog." + Usuarios.NM_USUARIO_BD + " WHERE ";

            if (pUsuario.getIdUsuario() != null) {
                query = query + conectorAND + Usuarios.NM_USUARIO_BD + ".\"" + Usuarios.NM_ID_USUARIO + "\" = ?";

                conectorAND = " AND ";
            }

            if (pUsuario.getNomeUsuario() != null) {
                query = query + conectorAND + Usuarios.NM_USUARIO_BD + ".\"" + Usuarios.NM_NOME_USUARIO + "\" = ?";

                conectorAND = " AND ";
            }

            if (pUsuario.getEmailUsuario() != null) {
                query = query + conectorAND + Usuarios.NM_USUARIO_BD + ".\"" + Usuarios.NM_EMAIL_USUARIO + "\" = ?";

                conectorAND = " AND ";
            }

            statement = dbConnection.createPreparedStatement(query);

            if (pUsuario.getIdUsuario() != null) {
                statement.setLong(++queryIndex, pUsuario.getIdUsuario());
            }

            if (pUsuario.getNomeUsuario() != null) {
                statement.setString(++queryIndex, pUsuario.getNomeUsuario());
            }

            if (pUsuario.getEmailUsuario() != null) {
                statement.setString(++queryIndex, pUsuario.getEmailUsuario());
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long idUsuario = resultSet.getLong(Usuarios.NM_ID_USUARIO);
                String nomeUsuario = resultSet.getString(Usuarios.NM_NOME_USUARIO);
                String emailUsuario = resultSet.getString(Usuarios.NM_EMAIL_USUARIO);
                String senhaUsuario = resultSet.getString(Usuarios.NM_SENHA_USUARIO);

                usuario = new Usuarios(nomeUsuario, emailUsuario, senhaUsuario);
                usuario.setIdUsuario(idUsuario); 
            }

            dbConnection.closeConnection();
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public void registrarUsuario(Usuarios usuario) {
        DatabaseConnection dbConnection = new DatabaseConnection();

        // Cria um statement
        PreparedStatement statement = null;

        try {
            String query = "INSERT INTO blog." + Usuarios.NM_USUARIO_BD + "(\"nomeUsuario\", \"emailUsuario\", \"senhaUsuario\") VALUES (?, ?, ?)";

            statement = dbConnection.createPreparedStatement(query);

            statement.setString(1, usuario.getNomeUsuario());
            statement.setString(2, usuario.getEmailUsuario());
            statement.setString(3, usuario.getSenhaUsuario());

            int linhasAfetadas = statement.executeUpdate();

            // Verifique se a inserção foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Inserção bem-sucedida!");
            } else {
                System.out.println("Falha na inserção.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Feche os recursos
            try {
                if (statement != null) statement.close();
                if (dbConnection != null) dbConnection.closeConnection();;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
