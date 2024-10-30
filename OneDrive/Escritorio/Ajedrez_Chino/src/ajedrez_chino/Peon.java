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
public class Peon extends Ficha {

    public Peon(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    public boolean movimientoValido(int nuevaFila, int nuevaColumna,Ficha[][]tablero) {
        if (nuevaFila == 5) {
            return false;
        }

        if (this.color.equals("Rojo")) {
            if (fila == 6) {
                if (nuevaFila == fila - 2 && nuevaColumna == columna) {
                    return true;
                }
            }

            if (nuevaFila == fila - 1 && nuevaColumna == columna) {
                return true;
            }

            if (fila < 5 && nuevaFila == fila && (nuevaColumna == columna - 1 || nuevaColumna == columna + 1)) {
                return true;
            }
        } else if (this.color.equals("Negro")) {

            if (fila == 4) {
                if (nuevaFila == fila + 2 && nuevaColumna == columna) {
                    return true;
                }
            }

            if (nuevaFila == fila + 1 && nuevaColumna == columna) {
                return true;
            }

            if (fila > 4 && nuevaFila == fila && (nuevaColumna == columna - 1 || nuevaColumna == columna + 1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ImageIcon obtenerIcono() {
        String ruta = color.equals("Rojo") 
            ? "/ajedrez_chino/imagenes/English-Pawn-Red.png" 
            : "/ajedrez_chino/imagenes/English-Pawn-Black.png";
        return new ImageIcon(getClass().getResource(ruta));
    }
}
