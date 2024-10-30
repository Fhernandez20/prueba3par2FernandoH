/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ajedrez_chino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author fdhg0
 */
public class TableroXiangqi extends JFrame {

    private JButton[][] botones;
    private Ficha[][] fichas;
    private Players loggedUser;
    private Players opponent;
    private Partidas partidas;

    // Reyes
    Rey reyNegro = new Rey("Negro", 0, 4);
    Rey reyRojo = new Rey("Rojo", 10, 4);
    private String turnoActual = "Rojo";
    private JLabel turnoLabel;

    // Oficiales
    Oficial oficialRojo1 = new Oficial("Rojo", 10, 3);
    Oficial oficialRojo2 = new Oficial("Rojo", 10, 5);
    Oficial oficialNegro1 = new Oficial("Negro", 0, 3);
    Oficial oficialNegro2 = new Oficial("Negro", 0, 5);

    // Elefantes
    Elefante elefanteRojo1 = new Elefante("Rojo", 10, 2);
    Elefante elefanteRojo2 = new Elefante("Rojo", 10, 6);
    Elefante elefanteNegro1 = new Elefante("Negro", 0, 2);
    Elefante elefanteNegro2 = new Elefante("Negro", 0, 6);

    // Peones
    Peon peonRojo1 = new Peon("Rojo", 7, 0);
    Peon peonRojo2 = new Peon("Rojo", 7, 2);
    Peon peonRojo3 = new Peon("Rojo", 7, 4);
    Peon peonRojo4 = new Peon("Rojo", 7, 6);
    Peon peonRojo5 = new Peon("Rojo", 7, 8);

    Peon peonNegro1 = new Peon("Negro", 3, 0);
    Peon peonNegro2 = new Peon("Negro", 3, 2);
    Peon peonNegro3 = new Peon("Negro", 3, 4);
    Peon peonNegro4 = new Peon("Negro", 3, 6);
    Peon peonNegro5 = new Peon("Negro", 3, 8);

    // Caballos
    Caballo caballoRojo1 = new Caballo("Rojo", 10, 1);
    Caballo caballoRojo2 = new Caballo("Rojo", 10, 7);
    Caballo caballoNegro1 = new Caballo("Negro", 0, 1);
    Caballo caballoNegro2 = new Caballo("Negro", 0, 7);

    // Torres
    Torre torreRoja1 = new Torre("Rojo", 10, 0);
    Torre torreRoja2 = new Torre("Rojo", 10, 8);
    Torre torreNegra1 = new Torre("Negro", 0, 0);
    Torre torreNegra2 = new Torre("Negro", 0, 8);

    // Cañones
    Canon canonRojo1 = new Canon("Rojo", 8, 1);
    Canon canonRojo2 = new Canon("Rojo", 8, 7);
    Canon canonNegro1 = new Canon("Negro", 2, 1);
    Canon canonNegro2 = new Canon("Negro", 2, 7);
    
    
    public TableroXiangqi(Players loggedUser, Players selectedOpponent) {
        this.partidas = new Partidas();
        this.loggedUser= loggedUser;
        this.opponent = selectedOpponent;
        

        try {
            loggedUser.setColor("Rojo");
            opponent.setColor("Negro");
           

            this.botones = new JButton[11][9];
            this.fichas = new Ficha[11][9];

            setLayout(new BorderLayout());

            JPanel tableroPanel = new JPanel(new GridLayout(11, 9));
            inicializarFichas();

            crearBotonesTablero(tableroPanel);

            JPanel panelBoton = new JPanel();
            JButton btnRetirarse = new JButton("Retirarse");
            btnRetirarse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String colorRendido = turnoActual;
                    rendirse(colorRendido);
                }
            });
            panelBoton.add(btnRetirarse);

            turnoLabel = new JLabel("Turno: " + turnoActual, SwingConstants.CENTER);
            turnoLabel.setFont(new Font("Arial", Font.BOLD, 16));

            JPanel panelLateral = new JPanel();
            panelLateral.setLayout(new BorderLayout());
            panelLateral.setPreferredSize(new Dimension(150, getHeight()));
            panelLateral.add(turnoLabel, BorderLayout.NORTH);

            add(tableroPanel, BorderLayout.CENTER);
            add(panelLateral, BorderLayout.EAST);
            add(panelBoton, BorderLayout.SOUTH);

            pack();
            setSize(800, 650);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al inicializar el tablero: " + e.getMessage());
        }
    }

    private final void crearBotonesTablero(JPanel tableroPanel) {
        for (int fila = 0; fila < 11; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                final int x = fila;
                final int y = columna;
                botones[fila][columna] = new JButton();

                if (esPalacio(fila, columna)) {
                    botones[fila][columna].setBackground(Color.PINK);
                } else if (esRio(fila, columna)) {
                    botones[fila][columna].setBackground(Color.CYAN);
                } else {
                    if ((fila + columna) % 2 == 0) {
                        botones[fila][columna].setBackground(Color.LIGHT_GRAY);
                    } else {
                        botones[fila][columna].setBackground(Color.WHITE);
                    }
                }

                if (fichas[fila][columna] != null) {
                    botones[fila][columna].setIcon(fichas[fila][columna].obtenerIcono());
                }

                botones[fila][columna].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        manejarClickBoton(x, y);
                    }
                });
                botones[fila][columna].setPreferredSize(new Dimension(60, 60));
                tableroPanel.add(botones[fila][columna]);
            }
        }
    }

    private void inicializarFichas() {
        fichas[0][4] = reyNegro;
        fichas[10][4] = reyRojo;

        // Oficiales
        fichas[10][3] = oficialRojo1;
        fichas[10][5] = oficialRojo2;
        fichas[0][3] = oficialNegro1;
        fichas[0][5] = oficialNegro2;

        // Elefantes
        fichas[10][2] = elefanteRojo1;
        fichas[10][6] = elefanteRojo2;
        fichas[0][2] = elefanteNegro1;
        fichas[0][6] = elefanteNegro2;

        // Torres
        fichas[10][0] = torreRoja1;
        fichas[10][8] = torreRoja2;
        fichas[0][0] = torreNegra1;
        fichas[0][8] = torreNegra2;

        // Cañones
        fichas[8][1] = canonRojo1;
        fichas[8][7] = canonRojo2;
        fichas[2][1] = canonNegro1;
        fichas[2][7] = canonNegro2;

        // Peones
        fichas[7][0] = peonRojo1;
        fichas[7][2] = peonRojo2;
        fichas[7][4] = peonRojo3;
        fichas[7][6] = peonRojo4;
        fichas[7][8] = peonRojo5;
        fichas[3][0] = peonNegro1;
        fichas[3][2] = peonNegro2;
        fichas[3][4] = peonNegro3;
        fichas[3][6] = peonNegro4;
        fichas[3][8] = peonNegro5;

        // Caballos
        fichas[10][1] = caballoRojo1;
        fichas[10][7] = caballoRojo2;
        fichas[0][1] = caballoNegro1;
        fichas[0][7] = caballoNegro2;
        
    }

    private Ficha fichaSeleccionada = null;

    private void manejarClickBoton(int x, int y) {
        try {
            if (fichaSeleccionada == null) {
                fichaSeleccionada = fichas[x][y];
                if (fichaSeleccionada != null) {
                    if (!fichaSeleccionada.getColor().equals(turnoActual)) {
                        JOptionPane.showMessageDialog(this, "No es tu turno. Es el turno del jugador " + turnoActual);
                        fichaSeleccionada = null;
                        return;
                    }
                    System.out.println("Seleccionaste la ficha: " + fichaSeleccionada.getClass().getSimpleName());
                }
            } else {
                if (fichaSeleccionada.movimientoValido(x, y, fichas)) {
                    if (fichas[x][y] == null || !fichas[x][y].getColor().equals(fichaSeleccionada.getColor())) {
                        if (fichas[x][y] != null && fichas[x][y] instanceof Rey) {
                            String colorGanador = turnoActual;
                            JOptionPane.showMessageDialog(this, "¡El rey ha sido capturado! Fin del juego. Ganador: " + colorGanador);

                            finalizarJuego(colorGanador);
                            return;
                        }
                        fichas[x][y] = fichaSeleccionada;
                        fichas[fichaSeleccionada.getFila()][fichaSeleccionada.getColumna()] = null;
                        fichaSeleccionada.setFila(x);
                        fichaSeleccionada.setColumna(y);
                        JOptionPane.showMessageDialog(this, "Moviste la ficha a: " + x + ", " + y);
                        actualizarBotones(botones, fichas);
                        turnoActual = turnoActual.equals("Rojo") ? "Negro" : "Rojo";
                        turnoLabel.setText("Turno: " + turnoActual);
                    } else {
                        JOptionPane.showMessageDialog(this, "Movimiento inválido: Ya hay una ficha de tu color en esa posición.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Movimiento inválido");
                }
                fichaSeleccionada = null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al manejar el clic: " + e.getMessage());
        }
    }

    private boolean esPalacio(int fila, int columna) {
        return (fila >= 0 && fila <= 2 && columna >= 3 && columna <= 5) || (fila >= 8 && fila <= 10 && columna >= 3 && columna <= 5);
    }

    private void actualizarBotones(JButton[][] botones, Ficha[][] tablero) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 9; j++) {
                if (fichas[i][j] != null) {
                    botones[i][j].setIcon(fichas[i][j].obtenerIcono());
                } else {
                    botones[i][j].setIcon(null);
                }
            }
        }

    }

    private boolean esRio(int fila, int columna) {
        return fila == 5;
    }

    private void rendirse(String colorRendido) {
        try {

            String colorGanador = colorRendido.equals("Rojo") ? "Negro" : "Rojo";
            JOptionPane.showMessageDialog(this, "El jugador " + colorRendido + " se ha rendido. Ganador: " + colorGanador);
            finalizarJuego(colorGanador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar la rendición: " + e.getMessage());
        }
    }

    private void finalizarJuego(String ganador) {
        try {
            incrementarPuntos(ganador);
            Partidas nuevaPartida = new Partidas();
            nuevaPartida.registerGame(loggedUser,opponent);
            panelprincipal pp = new panelprincipal();

            pp.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al finalizar el juego: " + e.getMessage());
        }
    }

    private void incrementarPuntos(String ganadorColor) {
        if (ganadorColor.equals("Rojo") && loggedUser.getColor().equals("Rojo") || ganadorColor.equals("Negro") && loggedUser.getColor().equals("Negro")) {
            loggedUser.incrementWins();
        } else {
            opponent.incrementWins();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
