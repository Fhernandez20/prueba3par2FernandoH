/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcados;

/**
 *
 * @author josue
 */
public abstract class JuegoAhorcadoBase implements JuegoAhorcado {
    protected String palabraSecreta;
    protected StringBuilder palabraActual;
    protected int intentos;

    public JuegoAhorcadoBase() {
        this.intentos = 5; 
        this.palabraActual = new StringBuilder();
    }

    protected abstract void actualizarPalabraActual(char letra);
    protected abstract boolean verificarLetra(char letra);

    protected boolean hasGanado() {
        return palabraActual.toString().equals(palabraSecreta);
        
    }
}
