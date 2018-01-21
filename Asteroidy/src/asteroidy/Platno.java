/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author student
 */
public class Platno extends JComponent implements MouseListener, MouseMotionListener, KeyListener, ActionListener{
    private Point p = new Point(100,100);
    static ArrayList<Objekt> points;
    static ArrayList<asteroid> asteroids;
    private Timer timer;
    public Image image;
    public Image ship;
    private Rectangle playerRect;
    public int rectWidth = 80;
    private float distance = 200f;
    private int kills = 0;
    private int score = 0;
    private Font myFont = new Font("Aerial", Font.BOLD, 18);
    private Font myFont2 = new Font("Aerial", Font.BOLD, 58);
    public Platno(){
        this.points = new ArrayList<Objekt>();
        this.asteroids = new ArrayList<asteroid>();
    }
    
    public void init() {
       this.addKeyListener(this);
        this.setFocusable(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this); 
        ship = new ImageIcon(getClass().getResource("ship.png")).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        this.timer = new Timer(30,this);
        timer.start();
    }
    
    public void drawPoints(Graphics g) {
        for (Objekt b: this.points) {
            b.paint(g);
        }
    }
    
    public void drawAxis(Graphics g, Dimension size){
        Graphics2D g2d = (Graphics2D) g;
        float tloustka = 1f;
        float miterLimit = 2f;
        float[] dashPattern = {5f};
        float dashPhase = 5f;
        BasicStroke stroke = new BasicStroke(tloustka,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,miterLimit,dashPattern,dashPhase);
        g2d.setStroke(stroke);
        g2d.setColor(Color.black);
        g2d.drawLine(0, p.y, size.width, p.y);
        g2d.drawLine(p.x, 0, p.x, size.height);
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.white);
        Dimension size = this.getSize();
        g.fillRect(0, 0, size.width, size.height);
    //    drawAxis(g, size);
    //    drawRectangle(g,size);
        drawPoints(g);
        drawCrosshair(g);
        drawPlayer(g,size);
        drawAsteroids(g);
        drawText(g,size);
    }
    
    public static void spawnAsteroid() {
        Random rand = new Random();
        int number = rand.nextInt(10);
        int num = rand.nextInt(2);
        if(num==1)
                    asteroids.add(new asteroid(Asteroidy.getWidth(),Asteroidy.getHeight() /number,80,80));
    	
    }
    
    public void drawText(Graphics g,Dimension size){
            if(timer.isRunning()){
            if((int)distance % 200 == 0)
                this.spawnAsteroid();
            String str = "light year: " + (int)(distance/2);
            score = (int)distance + kills * 200;
            String str2 = "Score: " + score;
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(myFont);
            g2.setColor(Color.green);
            g2.drawString(str, size.width-170, size.height-60);
            g2.drawString(str2, size.width-170, size.height-40);
            }
    }
    
 /*   public void drawRectangle(Graphics g, Dimension size) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.blue);
        g2d.fill(new Rectangle2D.Double(200, 200,30,30));        
                
    }
    */
    
    public void drawPlayer(Graphics g,Dimension size){
        if(timer.isRunning()){
        Graphics2D g2 = (Graphics2D) g;
        float tloustka = 3f;
      //  int rectwidth=80;
        int rectheight=80;
        BasicStroke stroke = new BasicStroke(tloustka,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
        g2.setStroke(stroke);
        g2.setColor(Color.blue);
     //  playerRect = new Rectangle(size.width/20,p.y-(rectheight/2),rectwidth,rectheight);
     //   g2.draw(playerRect);
        g2.drawImage(ship,size.width/20,p.y-(rectheight/2),null);
        }
    }
    
    public void setPoint(int x, int y, boolean fill) {
        p = new Point(x,y);
        this.points.add(new Projektil(p.x,p.y));
        
    }
    
    public void drawCrosshair(Graphics g){
       // if(timer.isRunning()){
            Graphics2D g2 = (Graphics2D) g;
            float tloustka = 2f;
            int crossW=30;
            int crossH=30;
            BasicStroke stroke = new BasicStroke(tloustka,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
            g2.setStroke(stroke);
            g2.setColor(Color.red);
            //g2d.drawLine(0, p.y, size.width, p.y);
            //g2d.drawLine(p.x, 0, p.x, size.height);
            g2.draw(new Rectangle2D.Double(p.x-crossW/2,p.y,crossW,0));
            g2.draw(new Rectangle2D.Double(p.x,p.y-crossH/2,0,crossH));
       // }
    }
    
    public void drawAsteroids(Graphics g){
        for(asteroid a: this.asteroids){
            a.paint(g);
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
       
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    /*    Objekt b = actObj;
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
        */
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        Iterator<Objekt> objIt;
        Iterator<asteroid> astIt = this.asteroids.iterator();
        
        while(astIt.hasNext()){
            asteroid a = astIt.next();
            
            if(!a.isActive()){
                astIt.remove();
            }else{
                objIt = this.points.iterator();
                while(objIt.hasNext()){
                    Objekt o = objIt.next();
                    if(a.detectCollision(o.getPoint(), o.getSize())){
                        a.hurt(o.getDamage());
                        o.die();
                        if(a.getHealth() <= 0){
                            a.die();
                            kills++;
                        }
                    }
                }
                a.animate();
                if(a.impact(playerRect)) {
                    timer.stop();                 
                }
            }
            
        }
        
        objIt = this.points.iterator();
        
        while(objIt.hasNext()){
            Objekt o = objIt.next();
            if(!o.isActive()){
                objIt.remove();
            }else{
                o.animate(this);
            }   
        }     
        
        this.repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {
        System.out.println(me.getButton());
        if (me.getButton() == 1) {
            this.setPoint(this.rectWidth + 40,me.getY(),true);
        }
      /*  if (me.getButton() == 2) {
            this.setPoint(me.getX(),this.getHeight()-70,false);
        }*/
        this.repaint();
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
       
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        p.x = me.getX();
        p.y = me.getY();
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        p.x = me.getX();
        p.y = me.getY();
        this.repaint();
    }
    
}
