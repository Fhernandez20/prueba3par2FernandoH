/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajedrez_chino;

/**
 *
 * @author fdhg0
 */
public class Caballo extends Ficha {

    public Caballo(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    public boolean movimientoValido(int nuevaFila, int nuevaColumna, Ficha[][] tablero) {
        if (nuevaFila != 5) {
            if (this.color.equals("Rojo")) {
                if ((nuevaFila == fila - 2 || nuevaFila == fila + 2) && (nuevaColumna == columna + 1 || nuevaColumna == columna - 1)) {
                    return true;
                } else if ((nuevaFila == fila - 1 || nuevaFila == fila + 1) && (nuevaColumna == columna + 2 || nuevaColumna == columna - 2)) {
                    return true;
                }
            } else if (this.color.equals("Negro")) {
                if ((nuevaFila == fila - 2 || nuevaFila == fila + 2) && (nuevaColumna == columna + 1 || nuevaColumna == columna - 1)) {
                    return true;
                } else if ((nuevaFila == fila - 1 || nuevaFila == fila + 1) && (nuevaColumna == columna + 2 || nuevaColumna == columna - 2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
