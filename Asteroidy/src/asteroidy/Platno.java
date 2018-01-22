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


@SuppressWarnings("serial")
public class Platno extends JComponent implements MouseListener, MouseMotionListener, KeyListener, ActionListener { 
   private Point p = new Point(100,100);
    static ArrayList<Objekt> points;
    static ArrayList<asteroid> asteroids;
    private Timer timer;
    public Image ship;
    public Image space;
    private Rectangle playerRect;
    private float distance = 1f;
    private int kills = 0;
    private int score = 0;
    public int rectWidth=80;
    private Font myFont = new Font("Aerial", Font.BOLD, 18);
    private Font myFont2 = new Font("Aerial", Font.BOLD, 58);
    public Platno(){
    	this.points = new ArrayList<Objekt>();
    	this.asteroids = new ArrayList<asteroid>();
        
    }
    
    public void setPoint(int x, int y,boolean fill){
        p = new Point(x,y);
        this.points.add(new Projektil(p.x,p.y));
    }
    
    public void createObject(int x, int y,boolean fill){
    	this.points.add(new Projektil(p.x,p.y));
    }
    
    public void setAxis(int x, int y){
        
    }
    
    public void init(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this); 
        this.addKeyListener(this);
        this.setFocusable(true);
        ship = new ImageIcon(getClass().getResource("ship.png")).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
    //    space = new ImageIcon(getClass().getResource("space.jpg")).getImage().getScaledInstance(1000,500, Image.SCALE_DEFAULT);
        this.timer = new Timer(30,this);
        timer.start();   
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
    
    public void drawPlayer(Graphics g,Dimension size){
        if(timer.isRunning()){
        Graphics2D g2 = (Graphics2D) g;
        float tloustka = 3f;
        
        int rectHeight=40;
        BasicStroke stroke = new BasicStroke(tloustka,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
        g2.setStroke(stroke);
        g2.setColor(Color.blue);
        playerRect = new Rectangle(40,p.y-(rectHeight/2),rectWidth,rectHeight);
        g2.draw(playerRect);
        g2.drawImage(ship,40,p.y-rectHeight,null);
        }
    }
    
     public void drawCrosshair(Graphics g){
        if(timer.isRunning()){
            Graphics2D g2 = (Graphics2D) g;
            float tloustka = 2f;
            int crossW=30;
            int crossH=30;
            BasicStroke stroke = new BasicStroke(tloustka,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
            g2.setStroke(stroke);
            g2.setColor(Color.green);
            //g2d.drawLine(0, p.y, size.width, p.y);
            //g2d.drawLine(p.x, 0, p.x, size.height);
            g2.draw(new Rectangle2D.Double(p.x-crossW/2,p.y,crossW,0));
            g2.draw(new Rectangle2D.Double(p.x,p.y-crossH/2,0,crossH));
        }
    }
     
    public void drawText(Graphics g,Dimension size){
        if(timer.isRunning()){
            if((int)distance % 30 == 0)
              //  this.spawnRndEnemyLine();
                this.spawnAsteroid();
            String str = "Kills: " + kills;
            score = (int)distance + kills * 150;
            String str2 = "Score: " + score;
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(myFont);
            g2.setColor(Color.green);
            g2.drawString(str, size.width-170, size.height-80);
            g2.drawString(str2, size.width-170, size.height-60);
        }
    }
    /*
    public static void spawnAsteroid() {
        Random rand = new Random();
        int number = rand.nextInt(10);
        int num = rand.nextInt(2);
        if(num==1)
                    enemies.add(new EnemyPlane1(Asteroidy.getWidth(),Asteroidy.getHeight() /number));
    	
    }
    */
    
    public static void spawnAsteroid() {
        Random rand = new Random();
        int count = 6;
        int spawn = 0;
    	for(int i = 0; i < count; i++) {         
                int num = rand.nextInt(2);
                if(num==1 && spawn == 0){
                    asteroids.add(new asterojd(Asteroidy.getWidth()-100,(Asteroidy.getHeight()/7) *i ));
                    spawn=1;
                }
    	}
        spawn =0;
    	
    }
    
    public void drawPoints(Graphics g){
        for(Objekt o: this.points){
            o.paint(g);
        }
    }
    
    public void drawEnemies(Graphics g){
        for(asteroid a: this.asteroids){
            a.paint(g);
        }
    }

    
    public void drawEndGame(Graphics g,Dimension size){
        if(!timer.isRunning()){
            String str = "Score: " + score;
            String str2 = "GAME OVER";
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(myFont2);
            g2.setColor(Color.red);     
            g2.drawString(str, size.width/2 -120, 290);
            g2.drawString(str2, size.width/2 -120, 220);
        }
    }
    
    
    public void paint(Graphics g){   
        g.setColor(Color.black);   
        g.drawImage(space, 0, 0, null);
        Dimension size = this.getSize();
        g.fillRect(0, 0, size.width, size.height);

        drawPlayer(g,size);
        drawCrosshair(g);
        drawText(g,size);
        drawPoints(g);
        drawEnemies(g);
       // drawEndGame(g,size);
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
        //System.out.println("x:"+me.getX()+" y:"+me.getY());
        p.x = me.getX();
        p.y = me.getY();
        this.repaint();
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
        }
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
                            a.die();
                            kills++;
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
        distance++;
        this.repaint();
    }
    
    public static void spawnRndEnemyLine() {
        Random rand = new Random();
        int count = 9;
    	for(int i = 0; i < count; i++) {         
                int num = rand.nextInt(2);
                if(num==1)
                    asteroids.add(new asterojd(((Asteroidy.getWidth() / count) * i) + (Asteroidy.getWidth() / count) / 2 - 40, -40));
    	}
    	
    }
    
}
