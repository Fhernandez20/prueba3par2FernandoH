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
public class Canon extends Ficha {

    public Canon(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    public boolean movimientoValido(int nuevaFila, int nuevaColumna, Ficha[][] tablero) {
        if (nuevaFila == fila || nuevaColumna == columna) {
            if (nuevaFila != 5) { 
                if (tablero[nuevaFila][nuevaColumna] == null) {
                    return !hayPiezasEnElCamino(nuevaFila, nuevaColumna, tablero, fila, columna);
                } else if (!tablero[nuevaFila][nuevaColumna].getColor().equals(this.color)) {
                    return hayUnaPiezaEnElCamino(nuevaFila, nuevaColumna, tablero, fila, columna);
                }
            }
        }
        return false;
    }

    private boolean hayPiezasEnElCamino(int nuevaFila, int nuevaColumna, Ficha[][] tablero, int filaActual, int columnaActual) {
        int filaIncremento = 0;
        int columnaIncremento = 0;

        if (nuevaFila == filaActual) {
            columnaIncremento = nuevaColumna > columnaActual ? 1 : -1;
        } else if (nuevaColumna == columnaActual) {
            filaIncremento = nuevaFila > filaActual ? 1 : -1;
        }

        filaActual += filaIncremento;
        columnaActual += columnaIncremento;
        while (filaActual != nuevaFila || columnaActual != nuevaColumna) {
            if (tablero[filaActual][columnaActual] != null) {
                return true;
            }
            filaActual += filaIncremento;
            columnaActual += columnaIncremento;
        }

        return false;
    }

    private boolean hayUnaPiezaEnElCamino(int nuevaFila, int nuevaColumna, Ficha[][] tablero, int filaActual, int columnaActual) {
        int filaIncremento = 0;
        int columnaIncremento = 0;

        if (nuevaFila == filaActual) {
            columnaIncremento = nuevaColumna > columnaActual ? 1 : -1;
        } else if (nuevaColumna == columnaActual) {
            filaIncremento = nuevaFila > filaActual ? 1 : -1;
        }

        int contadorPiezas = 0;
        filaActual += filaIncremento;
        columnaActual += columnaIncremento;
        while (filaActual != nuevaFila || columnaActual != nuevaColumna) {
            if (tablero[filaActual][columnaActual] != null) {
                contadorPiezas++;
            }
            filaActual += filaIncremento;
            columnaActual += columnaIncremento;
        }

        return contadorPiezas == 1;
    }

    @Override
    public ImageIcon obtenerIcono() {
        String ruta = color.equals("Rojo") 
            ? "/ajedrez_chino/imagenes/English-Cannon-Red.png" 
            : "/ajedrez_chino/imagenes/English-Cannon-Black.png";
        return new ImageIcon(getClass().getResource(ruta));
    }
}
