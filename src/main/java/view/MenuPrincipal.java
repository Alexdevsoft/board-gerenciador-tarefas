package view;

import java.util.Scanner;

public class MenuPrincipal {

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
                    System.out.println("Função para criar board ainda será implementada...");
                    break;
                case 2:
                    System.out.println("Função para selecionar board ainda será implementada...");
                    break;
                case 3:
                    System.out.println("Função para excluir board ainda será implementada...");
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
