/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy;

import java.awt.Graphics;

/**
 *
 * @author adolf
 */
public class Projektil extends Objekt{
    public int radius;
    public Projektil(int x , int y){
        super(x, y);
        this.radius = 4;
    }
    
    public void setSpeed(int speed){
        this.speed += speed;
    }
    
    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void setSize(int size){
        this.radius += size;
    }
    
    @Override
    public int getSize(){
        return this.radius;
    }

    @Override
    public boolean detect(int x, int y) {
        double a = Math.pow(x - this.point.x,2);
        double b = Math.pow(y - this.point.y,2);
        return Math.sqrt(a+b) <= this.radius;
    }
    

    @Override
    public void paint(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.point.x- this.radius,this.point.y- this.radius,2*this.radius,2*this.radius);
    }

    @Override
    public void animate(Platno p) {
        this.point.x += this.speed;
    }
    
}
