package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import model.Board;
import util.DBConnection;

public class BoardDAO {
	
	public int criarBoard(String nome) {
	    String sql = "INSERT INTO board (nome) VALUES (?)";
	    int boardId = -1;

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        stmt.setString(1, nome);
	        stmt.executeUpdate();

	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            boardId = rs.getInt(1);
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao criar board: " + e.getMessage());
	    }

	    return boardId;
	}

    public List<Board> listarBoards() {
        List<Board> boards = new ArrayList<>();
        String sql = "SELECT * FROM board";

        try (Connection conn = DBConnection.getConnection();
        	     PreparedStatement stmt = conn.prepareStatement(sql);
        	     ResultSet rs = stmt.executeQuery()) {
        	    
        	    while (rs.next()) {
        	        Board board = new Board();
        	        board.setId(rs.getInt("id"));
        	        board.setNome(rs.getString("nome"));
        	        boards.add(board);
        	    }

        } catch (SQLException e) {
            System.out.println("Erro ao listar boards: " + e.getMessage());
        }

        return boards;
    }

    public void excluirBoard(int id) {
        String sql = "DELETE FROM board WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Board excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir board: " + e.getMessage());
        }
    }

}
