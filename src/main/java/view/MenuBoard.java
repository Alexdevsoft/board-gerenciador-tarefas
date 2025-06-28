package view;

import java.time.LocalDateTime;
import java.util.Scanner;

import dao.CardDAO;
import dao.ColunaDAO;
import model.Card;

public class MenuBoard {
	
	private final int boardId;
    private final Scanner scanner = new Scanner(System.in);
    private final CardDAO cardDAO = new CardDAO();

    public MenuBoard(int boardId) {
        this.boardId = boardId;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n--- MENU DO BOARD ---");
            System.out.println("1 - Criar Card");
            System.out.println("2 - Listar Cards da Coluna Inicial");
            System.out.println("3 - Mover Card para próxima coluna");
            System.out.println("4 - Cancelar Card");
            System.out.println("5 - Bloquear Card");
            System.out.println("6 - Desbloquear Card");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarCard();
                    break;
                case 2:
                    listarCardsColunaInicial();
                    break;
                case 3:
                    moverCard();
                    break;
                case 4:
                    cancelarCard();
                    break;
                case 5:
                    bloquearCard();
                    break;
                case 6:
                    desbloquearCard();
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 3);
    }

    private void criarCard() {
        System.out.print("Título do card: ");
        String titulo = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        int colunaInicialId = new ColunaDAO().buscarColunaInicial(boardId);
        if (colunaInicialId == -1) {
            System.out.println("Coluna inicial não encontrada.");
            return;
        }

        Card card = new Card();
        card.setTitulo(titulo);
        card.setDescricao(descricao);
        card.setDataCriacao(LocalDateTime.now());
        card.setBloqueado(false);
        card.setMotivoBloqueio(null);
        card.setColunaId(colunaInicialId);

        cardDAO.criarCard(card);
    }

    private void listarCardsColunaInicial() {
        int colunaInicialId = new ColunaDAO().buscarColunaInicial(boardId);
        if (colunaInicialId == -1) {
            System.out.println("Coluna inicial não encontrada.");
            return;
        }

        System.out.println("Cards na coluna inicial:");
        for (Card c : cardDAO.listarCardsPorColuna(colunaInicialId)) {
            System.out.println("- [" + c.getId() + "] " + c.getTitulo() + " | " + c.getDescricao());
        }
    }
    
    private void moverCard() {
        System.out.print("Digite o ID do card que deseja mover: ");
        int cardId = scanner.nextInt();
        scanner.nextLine();

        Card card = cardDAO.buscarCardPorId(cardId);
        if (card == null) {
            System.out.println("Card não encontrado.");
            return;
        }

        if (card.isBloqueado()) {
            System.out.println("Card está bloqueado e não pode ser movido.");
            return;
        }

        ColunaDAO colunaDAO = new ColunaDAO();
        int novaColunaId = colunaDAO.buscarProximaColuna(card.getColunaId());

        if (novaColunaId == -1) {
            System.out.println("Não há próxima coluna disponível.");
            return;
        }

        boolean sucesso = cardDAO.moverCardParaColuna(cardId, novaColunaId);
        if (sucesso) {
            System.out.println("Card movido com sucesso para a próxima coluna!");
        } else {
            System.out.println("Erro ao mover card.");
        }
    }
    
    private void cancelarCard() {
        System.out.print("Digite o ID do card que deseja cancelar: ");
        int cardId = scanner.nextInt();
        scanner.nextLine();

        Card card = cardDAO.buscarCardPorId(cardId);
        if (card == null) {
            System.out.println("Card não encontrado.");
            return;
        }

        if (card.isBloqueado()) {
            System.out.println("Card está bloqueado e não pode ser cancelado.");
            return;
        }

        ColunaDAO colunaDAO = new ColunaDAO();
        int colunaCancelamentoId = colunaDAO.buscarColunaCancelamento(boardId);
        if (colunaCancelamentoId == -1) {
            System.out.println("Coluna de cancelamento não encontrada.");
            return;
        }

        boolean sucesso = cardDAO.moverCardParaColuna(cardId, colunaCancelamentoId);
        if (sucesso) {
            System.out.println("Card cancelado com sucesso!");
        } else {
            System.out.println("Erro ao cancelar card.");
        }
    }

    private void bloquearCard() {
        System.out.print("Digite o ID do card a bloquear: ");
        int cardId = scanner.nextInt();
        scanner.nextLine();

        Card card = cardDAO.buscarCardPorId(cardId);
        if (card == null) {
            System.out.println("Card não encontrado.");
            return;
        }
        if (card.isBloqueado()) {
            System.out.println("Card já está bloqueado.");
            return;
        }

        System.out.print("Motivo do bloqueio: ");
        String motivo = scanner.nextLine();

        boolean sucesso = cardDAO.bloquearCard(cardId, motivo);
        System.out.println(sucesso ? "Card bloqueado com sucesso!" : "Erro ao bloquear card.");
    }

    private void desbloquearCard() {
        System.out.print("Digite o ID do card a desbloquear: ");
        int cardId = scanner.nextInt();
        scanner.nextLine();

        Card card = cardDAO.buscarCardPorId(cardId);
        if (card == null) {
            System.out.println("Card não encontrado.");
            return;
        }
        if (!card.isBloqueado()) {
            System.out.println("Card não está bloqueado.");
            return;
        }

        System.out.print("Motivo do desbloqueio: ");
        String motivo = scanner.nextLine();

        boolean sucesso = cardDAO.desbloquearCard(cardId, motivo);
        System.out.println(sucesso ? "Card desbloqueado com sucesso!" : "Erro ao desbloquear card.");
    }


}
