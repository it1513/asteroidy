/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author student
 */
public class Platno extends JComponent implements KeyListener, ActionListener{
    private Point p = new Point(100,100);
    ArrayList<Objekt> points;
    private Timer timer;
    private Objekt actObj;
    public Platno(){
        this.points = new ArrayList();
    
    }
    
    public void init() {
    //    this.addMouseListener(this);
    //    this.addMouseMotionListener(this);
       this.addKeyListener(this);
        this.setFocusable(true);
        timer = new Timer(50,(ActionListener)this);
        timer.start();
    }
    
    public void drawPoints(Graphics g) {
        for (Objekt b: this.points) {
            b.paint(g);
        }
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.white);
        Dimension size = this.getSize();
        g.fillRect(0, 0, size.width, size.height);
    //    drawAxis(g, size);
        drawRectangle(g,size);
        drawPoints(g);
    }
    
    public void drawRectangle(Graphics g, Dimension size) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.blue);
        g2d.fill(new Rectangle2D.Double(200, 200,30,30));        
                
    }
    public void setPoint(int x, int y, boolean fill) {
        p = new Point(x,y);
        this.points.add(new Kruh(p.x, p.y, fill));
        this.actObj = this.points.get(this.points.size()-1);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
       
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Objekt b = actObj;
        switch(ke.getKeyCode()){
            case KeyEvent.VK_LEFT:
                System.out.println("Vlevo");
                b.point.x--;
                p.x--;
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Vpravo");
                b.point.x++;
                p.x++;
                break;
            case KeyEvent.VK_UP:
                System.out.println("Nahoru");
                b.point.y--;
                p.y--;
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Dolu");
                p.y++;
                b.point.y++;
                break;
               
        }
        this.repaint();
            
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for(Objekt t: this.points){
            t.animate(this);
        }
        this.repaint();
    }
    
}
