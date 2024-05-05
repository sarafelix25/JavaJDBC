package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.controller.Controller;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:banco.db";

        try {
            Connection connection = DriverManager.getConnection(url);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

        Controller controller = new Controller();
        controller.iniciar();
    }
}