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
public class Torre extends Ficha {

    public Torre(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    public boolean movimientoValido(int nuevaFila, int nuevaColumna, Ficha[][] tablero) {
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
            }
        }
        return false;
    }

    private boolean hayPiezasEnElCamino(int nuevaFila, int nuevaColumna, Ficha[][] tablero) {
        int filaIncremento = 0;
        int columnaIncremento = 0;

        if (nuevaFila == fila) {
            columnaIncremento = (nuevaColumna > columna) ? 1 : -1;
        } else if (nuevaColumna == columna) {
            filaIncremento = (nuevaFila > fila) ? 1 : -1;
        } else {
            return true;
        }
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

    @Override
    public ImageIcon obtenerIcono() {
        String ruta = color.equals("Rojo") 
            ? "/ajedrez_chino/imagenes/English-Rook-Red.png" 
            : "/ajedrez_chino/imagenes/English-Rook-Black.png";
        return new ImageIcon(getClass().getResource(ruta));
    }
    }

