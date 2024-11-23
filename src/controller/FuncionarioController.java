package controller;

import model.Funcionario;
import model.Desenvolvedor;
import model.Gerente;
import model.Treinador;
import model.GerenteDesenvolvedor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private final String arquivo = "funcionarios.txt";

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        salvarDados();
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    public void atualizarFuncionario(int id, String novoNome, double novoSalario) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                f.setNome(novoNome);
                f.setSalario(novoSalario);
                salvarDados();
                return;
            }
        }
        System.out.println("Funcionário não encontrado.");
    }

    public void excluirFuncionario(int id) {
        if (funcionarios.removeIf(f -> f.getId() == id)) {
            salvarDados();
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    public void salvarDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (Funcionario f : funcionarios) {
                bw.write(f.getId() + ";" + f.getClass().getSimpleName() + ";" + f.getNome() + ";" + f.getSalario());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        File file = new File(arquivo);
        if (!file.exists()) {
            System.out.println("Arquivo de dados não encontrado. Criando um novo arquivo...");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                try {
                    String[] dados = linha.split(";");
                    int id = Integer.parseInt(dados[0]);
                    String tipo = dados[1];
                    String nome = dados[2];
                    double salario = Double.parseDouble(dados[3]);

                    Funcionario funcionario = switch (tipo) {
                        case "Desenvolvedor" -> new Desenvolvedor(id, nome, salario);
                        case "Gerente" -> new Gerente(id, nome, salario);
                        case "Treinador" -> new Treinador(id, nome, salario);
                        case "GerenteDesenvolvedor" -> new GerenteDesenvolvedor(id, nome, salario);
                        default -> throw new IllegalArgumentException("Tipo desconhecido: " + tipo);
                    };

                    funcionarios.add(funcionario);
                } catch (Exception e) { // Captura todas as exceções
                    System.out.println("Erro ao processar linha: " + linha);
                    System.out.println("Detalhes do erro: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}

