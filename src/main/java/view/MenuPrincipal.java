package view;

import java.util.List;
import java.util.Scanner;

import dao.BoardDAO;
import dao.ColunaDAO;
import model.Board;

public class MenuPrincipal {

	private BoardDAO boardDAO = new BoardDAO();

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("--- MENU PRINCIPAL ---");
            System.out.println("1 - Criar novo Board");
            System.out.println("2 - Selecionar Board");
            System.out.println("3 - Excluir Board");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
            case 1:
                System.out.print("Digite o nome do novo board: ");
                String nome = scanner.nextLine();
                int novoId = boardDAO.criarBoard(nome);

                if (novoId != -1) {
                    ColunaDAO colunaDAO = new ColunaDAO();
                    colunaDAO.criarColunaPadrao(novoId);
                    System.out.println("Board e colunas padrão criados com sucesso!");
                } else {
                    System.out.println("Erro ao criar board.");
                }
                break;

                case 2:
                    List<Board> boards = boardDAO.listarBoards();
                    if (boards.isEmpty()) {
                        System.out.println("Nenhum board encontrado.");
                        break;
                    }

                    System.out.println("Boards disponíveis:");
                    for (Board b : boards) {
                        System.out.println(b.getId() + " - " + b.getNome());
                    }

                    System.out.print("Digite o ID do board para manipular: ");
                    int idSelecionado = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Você selecionou o board ID: " + idSelecionado);
                    // Aqui você pode chamar o Menu de manipulação de board depois
                    MenuBoard menuBoard = new MenuBoard(idSelecionado);
                    menuBoard.exibirMenu();
                    break;
                case 3:
                    System.out.print("Digite o ID do board que deseja excluir: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();
                    boardDAO.excluirBoard(idExcluir);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
        
        scanner.close();
        
    }
    
    

}
