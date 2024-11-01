/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcados;

/**
 *
 * @author josue
 */
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {
    
    
    public JuegoAhorcadoFijo(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta.toUpperCase();
        inicializarPalabraSecreta();
    }
    
    
    public String getPalabraSecreta(){
        return palabraSecreta;
    }
    
    @Override
    public void inicializarPalabraSecreta() {
        palabraActual = new StringBuilder("_".repeat(palabraSecreta.length()));
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
        if (palabraSecreta.contains(Character.toString(letra))) {
            actualizarPalabraActual(letra);
            return true;
        } else {
            intentos--;
            return false;
        }
    }
    
    @Override
    protected boolean hasGanado() {
        return palabraActual.toString().equals(palabraSecreta);
    }

    @Override
    public void jugar() {
    }
    
    
}
