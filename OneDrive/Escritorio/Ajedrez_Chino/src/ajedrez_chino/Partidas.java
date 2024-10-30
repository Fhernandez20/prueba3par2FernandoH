/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajedrez_chino;

import java.util.ArrayList;

/**
 *
 * @author josue
 */
public class Partidas implements GameManager {
    
    private ArrayList<String> logs; 
    private ArrayList<Players> partidas; 
    
    public Partidas() {
        this.logs = new ArrayList<>(); 
        this.partidas = new ArrayList<>(); 
    }

    public void registerGame(Players player1, Players player2) { 
        partidas.add(player1);
        partidas.add(player2);
        String log = "Match: " + player1.getUsername() + " vs " + player2.getUsername();
        
        player1.addLog(log);
        player2.addLog(log);
        
        logs.add(log);
    }

    public ArrayList<Players> getRanking() {
        ArrayList<Players> allPlayers = new ArrayList<>(Players.getAllPlayers());
        ordenarRanking(allPlayers, allPlayers.size());
        return allPlayers;
    }

    private void ordenarRanking(ArrayList<Players> players, int n) {
        if (n <= 1) {
            return;
        }

        ordenarRanking(players, n - 1);

        Players ultimo = players.get(n - 1);
        int j = n - 2;

        while (j >= 0 && players.get(j).getWins() < ultimo.getWins()) {
            players.set(j + 1, players.get(j));
            j--;
        }
        players.set(j + 1, ultimo);
    }

    public ArrayList<String> getLogs() {
        return logs;
    }
}
