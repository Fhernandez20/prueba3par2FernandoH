/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajedrez_chino;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author fdhg0
 */
public class Players {

    private String username;
    private int wins;
    private String password;
    private boolean loggedIn;
    private Calendar time;
    private String color;
    private ArrayList<String> logs; 


    private static ArrayList<Players> allPlayers = new ArrayList<>();

    private static Players currentLoggedUser = null;

    public Players(String usuario, String contraseña) {
        this.username = usuario;
        this.password = contraseña;
        this.loggedIn = false;
        this.color = color; 

        this.wins = 0;
        this.time = Calendar.getInstance();
        this.logs = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getpassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCalendar() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        return dateFormat.format(time.getTime());
    }

    public int getWins() {
        return wins;
    }
    
    public String getColor() {
        return color;
    }
    public void setColor(String Color){
       this.color=Color;
    }

    public void incrementWins() {
        wins+=3;
    }

    public static ArrayList<Players> getAllPlayers() {
        return allPlayers;
    }

    public boolean validatepassword(String inputpassword) {
        if (password.equals(inputpassword)) {
            return true;
        }
        return false;
    }

    public static void addPlayer(Players player) {
        allPlayers.add(player);
    }

    public static Players getCurrentLoggedUser() {
        return currentLoggedUser;
    }

    public static void setCurrentLoggedUser(Players player) {
        currentLoggedUser = player;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public static void removePlayer(Players player) {
        player.logout();
        allPlayers.remove(player);
    }

    public boolean Login(String password) {
        if (this.password.equals(password)) {
            this.loggedIn = true;
            return true;
        }
        return false;
    }

    public void logout() {
        if (this.loggedIn) {
            this.loggedIn = false;
            currentLoggedUser = null;
        }
    }
    
    public static Players getPlayerByName(String playerName) {
        ArrayList<Players> allPlayers = getAllPlayers();
        for (Players player : allPlayers) {
            if (player.getUsername().equalsIgnoreCase(playerName)) {
                return player;
            }
        }
        return null;
    }

    public void addLog(String log) {
        logs.add(log);
    }
     public ArrayList<String> getLogs() {
        return logs;
    }
}
