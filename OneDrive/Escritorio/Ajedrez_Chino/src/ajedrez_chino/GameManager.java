/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ajedrez_chino;

import java.util.ArrayList;

/**
 *
 * @author josue
 */
public interface GameManager {
    void registerGame(Players player1Name, Players player2Name); 
    ArrayList<Players> getRanking();  
}
