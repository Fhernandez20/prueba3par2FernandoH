/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajedrez_chino;

import javax.swing.ImageIcon;

/**
 *
 * @author fdhg0
 */
public class Elefante extends Ficha {

    public Elefante(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    public boolean movimientoValido(int nuevaFila, int nuevaColumna, Ficha[][] tablero) {
        if (this.color.equals("Rojo")) {
            if (nuevaFila > 5) {
                if (((nuevaFila == fila - 2 || nuevaFila == fila + 2) && (nuevaColumna == columna - 2 || nuevaColumna == columna + 2))) {
                    if (hayPiezasEnElCamino(nuevaFila, nuevaColumna, tablero)) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        } else if (this.color.equals("Negro")) {
            if (nuevaFila < 5) {
                if (((nuevaFila == fila - 2 || nuevaFila == fila + 2) && (nuevaColumna == columna - 2 || nuevaColumna == columna + 2))) {
                    if (hayPiezasEnElCamino(nuevaFila, nuevaColumna, tablero)) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean hayPiezasEnElCamino(int nuevaFila, int nuevaColumna, Ficha[][] tablero) {
        int filaIntermedia = (fila + nuevaFila) / 2;
        int columnaIntermedia = (columna + nuevaColumna) / 2;

        return tablero[filaIntermedia][columnaIntermedia] != null;
    }

    public ImageIcon obtenerIcono() {
        String ruta = color.equals("Rojo") 
            ? "/ajedrez_chino/imagenes/English-Elephant-Red.png" 
            : "/ajedrez_chino/imagenes/English-Elephant-Black.png";
        return new ImageIcon(getClass().getResource(ruta));
    }
}
