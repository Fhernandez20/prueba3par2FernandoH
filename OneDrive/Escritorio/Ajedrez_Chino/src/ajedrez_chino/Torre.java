/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajedrez_chino;

/**
 *
 * @author fdhg0
 */
public class Torre extends Ficha {

    public Torre(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    public boolean movimientoValido(int nuevaFila, int nuevaColumna,Ficha[][]tablero) {
        if (nuevaFila != 5) {
            if (this.color.equals("Rojo")) {
                if (nuevaFila == fila && (nuevaColumna >= 0 && nuevaColumna <= 8)) {
                    if (hayPiezasEnElCamino(nuevaFila, nuevaColumna, tablero)) {
                        return false;
                    }
                    return true;
                } else if (nuevaColumna == columna && (nuevaFila >= 0 && nuevaColumna <= 10)) {
                    if (hayPiezasEnElCamino(nuevaFila, nuevaColumna, tablero)) {
                        return false;
                    }
                    return true;
                }
            } else if (this.color.equals("Negro")) {
                if (nuevaFila == fila && (nuevaColumna >= 0 && nuevaColumna <= 8)) {
                    return true;
                } else if (nuevaColumna == columna && (nuevaFila >= 0 && nuevaColumna <= 10)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hayPiezasEnElCamino(int nuevaFila, int nuevaColumna, Ficha[][] tablero) {
        int filaIncremento = 0;
        int columnaIncremento = 0;

        if (nuevaFila == fila) {
        } else if (nuevaColumna == columna) {
            filaIncremento = nuevaFila > fila ? 1 : -1; 
        } else {
            return true;
        }

        // Recorrer las casillas intermedias
        int filaActual = fila + filaIncremento;
        int columnaActual = columna + columnaIncremento;

        while (filaActual != nuevaFila || columnaActual != nuevaColumna) {
            if (tablero[filaActual][columnaActual] != null) {
                return true; 
            }

            filaActual += filaIncremento;
            columnaActual += columnaIncremento;
        }

        return false;
    }
}
