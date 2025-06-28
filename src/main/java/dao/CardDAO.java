package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.Timestamp;

import model.Card;
import util.DBConnection;

public class CardDAO {

	public void criarCard(Card card) {
        String sql = "INSERT INTO card (titulo, descricao, data_criacao, bloqueado, motivo_bloqueio, coluna_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, card.getTitulo());
            stmt.setString(2, card.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(card.getDataCriacao()));
            stmt.setBoolean(4, card.isBloqueado());
            stmt.setString(5, card.getMotivoBloqueio());
            stmt.setInt(6, card.getColunaId());

            stmt.executeUpdate();
            System.out.println("Card criado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar card: " + e.getMessage());
        }
    }

    public List<Card> listarCardsPorColuna(int colunaId) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM card WHERE coluna_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, colunaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Card card = new Card();
                card.setId(rs.getInt("id"));
                card.setTitulo(rs.getString("titulo"));
                card.setDescricao(rs.getString("descricao"));
                card.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                card.setBloqueado(rs.getBoolean("bloqueado"));
                card.setMotivoBloqueio(rs.getString("motivo_bloqueio"));
                card.setColunaId(rs.getInt("coluna_id"));

                cards.add(card);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar cards: " + e.getMessage());
        }

        return cards;
    }
    
    public boolean moverCardParaColuna(int cardId, int novaColunaId) {
        String sql = "UPDATE card SET coluna_id = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novaColunaId);
            stmt.setInt(2, cardId);
            int atualizado = stmt.executeUpdate();

            return atualizado > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao mover card: " + e.getMessage());
            return false;
        }
    }

    public Card buscarCardPorId(int id) {
        String sql = "SELECT * FROM card WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Card card = new Card();
                card.setId(rs.getInt("id"));
                card.setTitulo(rs.getString("titulo"));
                card.setDescricao(rs.getString("descricao"));
                card.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                card.setBloqueado(rs.getBoolean("bloqueado"));
                card.setMotivoBloqueio(rs.getString("motivo_bloqueio"));
                card.setColunaId(rs.getInt("coluna_id"));
                return card;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar card: " + e.getMessage());
        }

        return null;
    }
    
    public boolean bloquearCard(int cardId, String motivo) {
        String sql = "UPDATE card SET bloqueado = 1, motivo_bloqueio = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, motivo);
            stmt.setInt(2, cardId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao bloquear card: " + e.getMessage());
            return false;
        }
    }

    public boolean desbloquearCard(int cardId, String motivoDesbloqueio) {
        String sql = "UPDATE card SET bloqueado = 0, motivo_bloqueio = CONCAT(motivo_bloqueio, '\\nDesbloqueado: ', ?) WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, motivoDesbloqueio);
            stmt.setInt(2, cardId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao desbloquear card: " + e.getMessage());
            return false;
        }
    }


}
