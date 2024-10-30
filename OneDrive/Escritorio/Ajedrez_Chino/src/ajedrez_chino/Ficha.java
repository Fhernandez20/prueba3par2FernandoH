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
public abstract class Ficha {
    
    protected String color; 
    protected int fila,columna;
    protected ImageIcon icono; 


   public Ficha(String color, int fila, int columna) { 
        this.color = color;
        this.fila = fila; 
        this.columna = columna; 
        this.icono = obtenerIcono();

    }

    public String getColor() {
        return color;
    }

    public int getFila() { 
        return fila;
    }

    public int getColumna() { 
        return columna;
    }
    
    public void setFila(int nuevaFila){ 
        this.fila = nuevaFila;
    }
    
    public void setColumna(int nuevaColumna){ 
        this.columna = nuevaColumna;
    }

 
    
    public void setPosicion(int fila, int columna) { 
        this.fila = fila; 
        this.columna = columna; 
    }
    
    public ImageIcon obtenerIcono(){
        return icono;
    }
    


    public abstract boolean movimientoValido(int nuevaFila, int nuevaColumna,Ficha[][]tablero); 
}
