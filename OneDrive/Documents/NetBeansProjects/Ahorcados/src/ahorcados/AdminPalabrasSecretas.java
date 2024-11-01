/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcados;

/**
 *
 * @author josue
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdminPalabrasSecretas {
    private List<String> palabras;

    public AdminPalabrasSecretas() {
        palabras = new ArrayList<>();
        //Palabras Predeterminadas
        palabras.add("FERNANDO");
        palabras.add("STEVE");
        palabras.add("INGERICK");
        palabras.add("ROMA");
    }

    public void agregarPalabra(String palabra) {
        palabras.add(palabra.toUpperCase());
    }

    public String obtenerPalabraAleatoria() {
        Random random = new Random();
        return palabras.get(random.nextInt(palabras.size()));
    }

    public List<String> getPalabras() {
        return palabras;
    }
}
