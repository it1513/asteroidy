/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author adolf
 */
public abstract class asteroid {
    
    protected Rectangle bounds;
    protected int speed  = 3;
    protected int health = 5;
    protected boolean active = true;
    
    public asteroid(int x, int y, int w, int h) {
        this.bounds = new Rectangle(x, y, w, h);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int rychlost) {
        speed = rychlost;
    }

    public int getHealth() {
        return health;
    }
    
    public void hurt(int damage){
        this.health -= damage;
    }
    
    public boolean detectCollision(Point p, int r){
        return (this.bounds.contains(new Point(p.x, p.y - r)));
    }
    
    public void die(){
        this.active = false;
    }
    
    public void animate(){
        this.bounds.x -= this.speed;
    }

    public boolean isActive() {
        return active;
    }
    
    public boolean impact(Rectangle rect) {
    	return rect.intersects(bounds);
    }
    
    public abstract void paint(Graphics g);
    
}