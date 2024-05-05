package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import model.Usuario;
import view.View;

public class Controller {
    private static final String URL = "jdbc:sqlite:banco.db";

    private Connection connection;
    private View view;

    public Controller() {
        view = new View();
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
