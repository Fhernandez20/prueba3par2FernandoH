/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajedrez_chino;

/**
 *
 * @author fdhg0
 */
public class Canon extends Ficha {
    public Canon(String color, int fila,int columna){
        super(color,fila,columna);
    }
    public boolean movimientoValido(int nuevaFila, int nuevaColumna,Ficha[][]tablero){
        if(nuevaFila!=5){
            if(this.color.equals("Rojo")){
                if(nuevaFila==fila && (nuevaColumna>=0 && nuevaColumna<=8)){
                    return true;
                }else if(nuevaColumna==columna && (nuevaFila>=0 && nuevaColumna<=10)){
                    return true;
                }
            }else if(this.color.equals("Negro")){
                if(nuevaFila==fila && (nuevaColumna>=0 && nuevaColumna<=8)){
                    return true;
                }else if(nuevaColumna==columna && (nuevaFila>=0 && nuevaColumna<=10)){
                    return true;
                }
            }
        }
        return false;
    }
}
