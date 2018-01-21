/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


/**
 *
 * @author student
 */
abstract class Objekt {
    protected Point point;
    protected Color color;
    protected int speed;
    protected boolean fill;
    protected boolean active = true;
    protected int damage = 1;
    
    
            
    public Objekt(int x, int y) {
        this.point = new Point(x, y);
        this.color = new Color(255, 0, 255);
        this.fill = fill;
        this.speed = 5;
        this.color = new Color(0,255,0);
        
    }
    
    public Point getPoint() {
        return point;
    }
    
    public void die(){
        this.active = false;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public int getDamage() {
        return damage;
    }
   
    
    abstract public void setSpeed(int speed);
    abstract public int getSpeed();
    abstract public boolean detect(int x, int y);
    abstract public void paint(Graphics g);
    abstract public void animate(Platno p);
    abstract public void setSize(int size);
    abstract public int getSize();
}
