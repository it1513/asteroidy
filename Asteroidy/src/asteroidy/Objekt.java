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
    protected boolean fill;
    protected int vc; //velocity
    protected boolean active = true;
    
            
    public Objekt(int x, int y, boolean fill) {
        this.point = new Point(x, y);
        this.color = new Color(255, 0, 255);
        this.fill = fill;
        this.vc = 5;
        
    }
   /* public boolean setActive(int curX,int curY){
        if(this.detect(curX, curY)){
            this.active = true;
        }
        else{
            this.active = false;
        }
        return this.active;
    }*/
    
    abstract public void setSize(int size);
    abstract public void paint(Graphics g);
    abstract public void animate(Platno p);
    abstract public void setVC(int vc);
    abstract public boolean detect(int curX, int curY);
}
