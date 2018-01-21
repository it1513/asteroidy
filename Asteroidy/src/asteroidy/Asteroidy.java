/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author student
 */
public class Asteroidy {
    private static JFrame window;
    private JButton drawButton;
    private JLabel headLabel;
    private Platno platno;

    public Asteroidy(String title, int width, int height) {
        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(width, height);
        /* Vytvoření nápisu okna */
        headLabel = new JLabel(title);
        // Nastavení písma nadpisu
        int style = Font.BOLD | Font.ITALIC;
        Font font = new Font("Arial", style , 20);
        headLabel.setFont(font);
        // Nastavení barvy pozadí a písma
        headLabel.setBackground(Color.black);
        headLabel.setOpaque(true);
        headLabel.setForeground(Color.green);
        // Nastavení zarovnání a odsazení
        headLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        headLabel.setHorizontalAlignment(JLabel.CENTER);        
        /* Vytvoření objektu plátna */
        platno = new Platno();
        platno.init();
        
        /* Uložení kontejneru okna do proměnné pane */
        Container pane = window.getContentPane();
        /* Vložení grafických objektů do jednotlivých
        oblastí okna */
        pane.add(headLabel, BorderLayout.PAGE_START);
        pane.add(platno, BorderLayout.CENTER);
        
        window.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         new Asteroidy("asteroidy", 1000, 500);
        
    }
    
    public static int getHeight() {
    	return window.getHeight();
    }
    
    public static int getWidth() {
    	return window.getWidth();
    }
    
}
