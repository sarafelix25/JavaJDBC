package com.example.view;

import com.example.model.Usuario;
import java.util.List;

public class View {
    public void exibirMenu() {}

    public void exibirJogadoras(List<Usuario> jogadoras) {
        System.out.println("Lista de jogadoras do time feminino do Corinthians:");
        for (Usuario jogadora: jogadoras) {
            System.out.println("id: " + jogadora.getId() + ", Nome: " + jogadora.getName() + ", Posição: " + jogadora.getPosition());
        }
    }
}
