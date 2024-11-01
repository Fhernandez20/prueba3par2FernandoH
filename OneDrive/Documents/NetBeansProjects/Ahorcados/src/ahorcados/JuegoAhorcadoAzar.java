/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcados;

/**
 *
 * @author josue
 */
import java.util.List;
import java.util.Random;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {

    private List<String> palabrasPosibles;

    public JuegoAhorcadoAzar(List<String> palabrasPosibles) {
        this.palabrasPosibles = palabrasPosibles;
        inicializarPalabraSecreta();
    }

    @Override
    public void inicializarPalabraSecreta() {
        Random random = new Random();
        palabraSecreta = palabrasPosibles.get(random.nextInt(palabrasPosibles.size()));
        for (int i = 0; i < palabraSecreta.length(); i++) {
            palabraActual.append("_");
        }
    }

    @Override
    protected void actualizarPalabraActual(char letra) {
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraActual.setCharAt(i, letra);
            }
        }
    }

    @Override
    protected boolean verificarLetra(char letra) {
        boolean acierto = palabraSecreta.indexOf(letra) >= 0;
        if (acierto) {
            actualizarPalabraActual(letra);
        } else {
            intentos--;
        }
        return acierto;
    }

    @Override
    public void jugar() {
    }
    
}