/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcados;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author josue
 */
public class ahorcadospanel extends JFrame {

    public JPanel mainPanel;
    public JPanel panelInicio;
    public JPanel panelFija;
    public JPanel panelRandom;
    public CardLayout cardLayout;

    public ahorcadospanel() {
        this.setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initiateComponents();
    }

    private void initiateComponents() {
        //CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        this.getContentPane().add(mainPanel);

        //PanelInicio//
        panelInicio = new JPanel();
        panelInicio.setLayout(null);
        JLabel titulo = new JLabel("Ahorcados");
        titulo.setBounds(210, 10, 300, 80);
        titulo.setForeground(Color.BLACK);
        panelInicio.add(titulo);

        addPanels();

        addButtonsMain();

        mainPanel.add(panelInicio, "Inicio");
        mainPanel.add(panelFija, "Fija");
        mainPanel.add(panelRandom, "Random");

        cardLayout.show(mainPanel, "Inicio");
    }

    private void addPanels() {
        panelFija = new JPanel();
        panelFija.setLayout(null);
        panelFija.setBackground(Color.LIGHT_GRAY);

        panelRandom = new JPanel();
        panelRandom.setLayout(null);
        panelRandom.setBackground(Color.darkGray);
        addInterfacePanels();
    }

    private void addButtonsMain() {
        JButton botonFija = new JButton("Palabra Fija");
        botonFija.setBounds(60, 350, 120, 40);
        panelInicio.add(botonFija);

        JButton botonRandom = new JButton("Al azar");
        botonRandom.setBounds(300, 350, 120, 40);
        panelInicio.add(botonRandom);

        JButton botonAdmin = new JButton("Administrar Palabras");
        botonAdmin.setBounds(180, 200, 120, 40);
        panelInicio.add(botonAdmin);

        botonFija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabraSecreta = JOptionPane.showInputDialog(null, "Ingresa la palabra secreta:");
                if (palabraSecreta != null && !palabraSecreta.trim().isEmpty()) {
                    System.out.println("Palabra secreta: " + palabraSecreta);
                    cardLayout.show(mainPanel, "Fija");
                }
            }
        });

        botonRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Juego con palabra aleatoria iniciado.");
                cardLayout.show(mainPanel, "Random");
            }
        });

        botonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Panel de administración de palabras activado.");
            }
        });
    }

    private void addInterfacePanels() {
        //botones
        JButton registrarPalabraFija = new JButton("INSERTAR");
        registrarPalabraFija.setBounds(300, 350, 120, 40);
        panelFija.add(registrarPalabraFija);

        JButton registrarPalabraRandom = new JButton("INSERTAR");
        registrarPalabraRandom.setBounds(300, 350, 120, 40);
        panelRandom.add(registrarPalabraRandom);

        //Jfields
        JTextField ingresarLetraFija = new JTextField();
        ingresarLetraFija.setBounds(50, 355, 200, 30);
        panelFija.add(ingresarLetraFija);

        JTextField ingresarLetraRandom = new JTextField();
        ingresarLetraRandom.setBounds(50, 355, 200, 30);
        panelRandom.add(ingresarLetraRandom);
        
        //Jlabels
        JLabel palabraLabelFija = new JLabel("Palabra Fija:");
        palabraLabelFija.setBounds(50, 320, 200, 30);
        palabraLabelFija.setForeground(Color.BLACK);
        panelFija.add(palabraLabelFija);

        JLabel palabraLabelRandom = new JLabel("Palabra Aleatoria:");
        palabraLabelRandom.setBounds(50, 320, 200, 30);
        palabraLabelRandom.setForeground(Color.WHITE);
        panelRandom.add(palabraLabelRandom);
        
        JLabel palabraInvisibleFija = new JLabel("AAAAAAAAAAAAAAAAA");
        palabraInvisibleFija.setBounds(50, 180, 300, 40); 
        palabraInvisibleFija.setForeground(Color.BLACK);
        palabraInvisibleFija.setFont(new Font("Arial", Font.BOLD, 24)); 
        panelFija.add(palabraInvisibleFija);
        
        JLabel palabraInvisible = new JLabel("AAAAAAAAAAAAAAAAA");
        palabraInvisible.setBounds(50, 180, 300, 40); 
        palabraInvisible.setForeground(Color.WHITE);
        palabraInvisible.setFont(new Font("Arial", Font.BOLD, 24)); 
        panelRandom.add(palabraInvisible);
    }

}
