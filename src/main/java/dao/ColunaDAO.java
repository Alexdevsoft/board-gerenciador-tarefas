package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TipoColuna;
import util.DBConnection;

public class ColunaDAO {
	
	public void criarColunaPadrao(int boardId) {
        String sql = "INSERT INTO coluna (nome, ordem, tipo, board_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // INICIAL
            stmt.setString(1, "A Fazer");
            stmt.setInt(2, 1);
            stmt.setString(3, TipoColuna.INICIAL.name());
            stmt.setInt(4, boardId);
            stmt.executeUpdate();

            // FINAL
            stmt.setString(1, "Concluído");
            stmt.setInt(2, 2);
            stmt.setString(3, TipoColuna.FINAL.name());
            stmt.setInt(4, boardId);
            stmt.executeUpdate();

            // CANCELAMENTO
            stmt.setString(1, "Cancelado");
            stmt.setInt(2, 3);
            stmt.setString(3, TipoColuna.CANCELAMENTO.name());
            stmt.setInt(4, boardId);
            stmt.executeUpdate();

            System.out.println("Colunas padrão criadas com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar colunas: " + e.getMessage());
        }
    }
	
	public int buscarColunaInicial(int boardId) {
	    String sql = "SELECT id FROM coluna WHERE board_id = ? AND tipo = 'INICIAL'";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, boardId);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id");
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao buscar coluna inicial: " + e.getMessage());
	    }

	    return -1;
	}
	
	public int buscarProximaColuna(int colunaAtualId) {
	    String sql = """ SELECT c2.id FROM coluna c1 JOIN coluna c2 ON c1.board_id = c2.board_id WHERE c1.id = ? AND c2.ordem = c1.ordem + 1 """;

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, colunaAtualId);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id");
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao buscar próxima coluna: " + e.getMessage());
	    }

	    return -1;
	}
	
	public int buscarColunaCancelamento(int boardId) {
	    String sql = "SELECT id FROM coluna WHERE board_id = ? AND tipo = 'CANCELAMENTO'";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, boardId);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id");
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao buscar coluna de cancelamento: " + e.getMessage());
	    }

	    return -1;
	}




}
