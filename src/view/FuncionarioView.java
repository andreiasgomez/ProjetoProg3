package view;

import controller.FuncionarioController;
import model.*;

import java.util.Scanner;

public class FuncionarioView {
    private final FuncionarioController controller = new FuncionarioController();

    public void iniciar() {
        controller.carregarDados();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n1. Adicionar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Atualizar Funcionário");
            System.out.println("4. Excluir Funcionário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            
            // Verifica se a entrada é válida
            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida! Digite um número:");
                scanner.next();
            }
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> adicionarFuncionario(scanner);
                case 2 -> listarFuncionarios();
                case 3 -> atualizarFuncionario(scanner);
                case 4 -> excluirFuncionario(scanner);
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
        scanner.close();
    }

    private void adicionarFuncionario(Scanner scanner) {
        System.out.println("Selecione o tipo de funcionário:");
        System.out.println("1. Desenvolvedor");
        System.out.println("2. Gerente");
        System.out.println("3. Treinador");
        System.out.println("4. Gerente Desenvolvedor");
        
        int tipo = scanner.nextInt();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();

        Funcionario funcionario = switch (tipo) {
            case 1 -> new Desenvolvedor(id, nome, salario);
            case 2 -> new Gerente(id, nome, salario);
            case 3 -> new Treinador(id, nome, salario);
            case 4 -> new GerenteDesenvolvedor(id, nome, salario);
            default -> null;
        };

        if (funcionario != null) {
            controller.adicionarFuncionario(funcionario);
            System.out.println("Funcionário adicionado com sucesso.");
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private void listarFuncionarios() {
        System.out.println("Lista de Funcionários:");
        for (Funcionario f : controller.listarFuncionarios()) {
            System.out.println(f.mostrarDetalhes());
        }
    }

    private void atualizarFuncionario(Scanner scanner) {
        System.out.print("ID do funcionário a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo salário: ");
        double salario = scanner.nextDouble();
        controller.atualizarFuncionario(id, nome, salario);
    }

    private void excluirFuncionario(Scanner scanner) {
        System.out.print("ID do funcionário a excluir: ");
        int id = scanner.nextInt();
        controller.excluirFuncionario(id);
    }
}
