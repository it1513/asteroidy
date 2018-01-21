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
public class asteroid {
    
    protected Rectangle rect;
    protected int speed  = -3;
    protected boolean active = true;
    private Image asteroid;
    protected int health = 5;
    
    public asteroid(int x, int y, int w, int h) {
        this.rect = new Rectangle(x, y, w, h);
        asteroid = new ImageIcon(getClass().getResource("asteroid.png")).getImage().getScaledInstance(this.rect.width, this.rect.height, Image.SCALE_DEFAULT);
    }
    
    public void paint(Graphics g) {
        g.drawImage(asteroid, this.rect.x, this.rect.y-20, this.rect.width, this.rect.height+40, null);
        g.setColor(Color.BLUE);
        g.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
    
    public Rectangle getRect() {
        return rect;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void hurt(int damage){
        this.health -= damage;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setSpeed(int rychlost) {
        speed = rychlost;
    }
    
    public boolean detectCollision(Point p, int w){
        return (this.rect.contains(new Point(p.x + w, p.y)));
    }
    
    public void die(){
        this.active = false;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void animate(){
        this.rect.x += this.speed;
    }
    
    public boolean impact(Rectangle bounds) {
    	return bounds.intersects(rect);
    }
    
}
