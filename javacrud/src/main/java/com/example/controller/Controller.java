package com.example.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.model.Usuario;
import com.example.view.View;

public class Controller {
    private View view;
    private Connection connection;
    private Scanner scanner;

    public Controller() {
        view = new View();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:banco.db");
            scanner = new Scanner(System.in);
        } catch  (SQLException e) {
            e.printStackTrace();
        }
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            int opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1:
                    listarJogadoras();
                    break;
                case 2: 
                    adicionarJogadora();
                    break;
                case 3:
                    atualizarJogadora();
                    break;
                case 4:
                    excluirJogadora();
                    break;
                case 5:
                    continuar = false;
                    encerrar();
                    break;
                default:
                    System.out.println("Opção inválida. Escolha uma das opções.");
            }
        }
        // List<Usuario> jogadoras = obterJogadoras();
        // view.exibirJogadoras(jogadoras);
    }

    // public List<Usuario> obterJogadoras() {
    //     List<Usuario> jogadoras = new ArrayList<>();
    //     jogadoras.add(new Usuario(1, "Sara", "Meio"));
    //     jogadoras.add(new Usuario(2, "Paulinha", "Lateral"));
    //     jogadoras.add(new Usuario(3, "Cássia", "Atacante"));
    //     return jogadoras;
    // }

    private void exibirMenu() {
        System.out.println("Jogadoras Futebol Feminino Corinthians");
        System.out.println("1. Listar jogadoras");
        System.out.println("2. Adicionar jogadoras");
        System.out.println("3. Atualizar jogadoras");
        System.out.println("4. Excluir jogadoras");
        System.out.println("5. Sair");
        System.out.println("Escolha uma opção: ");
    }

    private void listarJogadoras() {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Jogadoras");
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Jogadoras");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("name") + ", Posição: " + rs.getString("position"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adicionarJogadora() {
        System.out.println("Nome da jogadora: ");
        String name = scanner.nextLine();
        System.out.println("Posição da jogadora: ");
        String position = scanner.nextLine();
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Jogadoras (name, position) VALUES (?, ?)");
            pstmt.setString(1, name);
            pstmt.setString(2, position);
            pstmt.executeUpdate();
            System.out.println("Jogadora adicionada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void atualizarJogadora() {
        System.out.println("ID da jogadora a ser atualizad: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nome da nova jogadora: ");
        String newName = scanner.nextLine();
        System.out.println("Posição da nova jogadora: ");
        String newPosition = scanner.nextLine();
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE Jogadoras SET name = ?, position = ? WHERE id = ?");
            pstmt.setString(1, newName);
            pstmt.setString(2, newPosition);
            pstmt.setInt(3, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Jogadora atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma jogadora encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void excluirJogadora() {
        System.out.println("ID da jogadora a ser excluída: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Jogadoras WHERE id = ?");
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Jogadora excluída com sucesso!");
            } else {
                System.out.println("Nenhuma jogadora encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void encerrar() {
        try {
            connection.close();
            scanner.close();
            System.out.println("Programa encerrado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
