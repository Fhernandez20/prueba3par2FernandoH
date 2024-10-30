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
public class Rey extends Ficha {

    public Rey(String color, int fila, int columna) {
        super(color, fila, columna);

    }

    public boolean movimientoValido(int nuevaFila, int nuevaColumna,Ficha[][]tablero) {
        if (this.color.equals("Rojo")) {
            if (nuevaFila >= 8 && nuevaFila <= 10 && nuevaColumna >= 3 && nuevaColumna <= 5) {
                if ((nuevaFila == fila && nuevaColumna == columna - 1) || (nuevaFila == fila && nuevaColumna == columna + 1)) {
                    return true;
                }

                if ((nuevaFila == fila - 1 && nuevaColumna == columna) || (nuevaFila == fila + 1 && nuevaColumna == columna)) {
                    return true;
                }
            }
        } else if (this.color.equals("Negro")) {
            if (nuevaFila <= 2 && nuevaFila >= 0 && nuevaColumna >= 3 && nuevaColumna <= 5) {
                if((nuevaFila == fila && nuevaColumna == columna - 1) || (nuevaFila == fila && nuevaColumna == columna + 1)) {
                    return true;
                }

                if ((nuevaFila == fila - 1 && nuevaColumna == columna) || (nuevaFila == fila + 1 && nuevaColumna == columna)) {
                    return true;
                }

            }

        }
        return false;
    }

    @Override
    public ImageIcon obtenerIcono() {
        String ruta = color.equals("Rojo") 
            ? "/ajedrez_chino/imagenes/English-King-Red.png" 
            : "/ajedrez_chino/imagenes/English-King-Black.png";
        return new ImageIcon(getClass().getResource(ruta));
    }
}
