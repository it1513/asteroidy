/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author student
 */
public class Kruh extends Objekt {

    private int radius;
    private Image image;

    public Kruh(int x, int y, boolean fill) {
        super(x, y, fill);
        this.radius = (int) Math.floor(Math.random() * 21) + 10;
        try{
            this.image = ImageIO.read(this.getClass().getResource("image.png"));
        }catch(IOException ex){
        
        }
    }

    @Override
    public void setSize(int size) {
        if (this.radius + size < 3 || this.radius + size > 200) {
            System.out.println("OVerSize");
        } else {
            this.radius += size;
        }
    }

    @Override
    public void paint(Graphics g) {
        if (this.active) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(this.color);
        }
        if (this.fill) {
            g.fillOval(this.point.x - this.radius,
                    this.point.y - this.radius,
                    2 * this.radius, 2 * this.radius);
        } else {
            g.drawOval(this.point.x - this.radius,
                    this.point.y - this.radius,
                    2 * this.radius, 2 * this.radius);
            g.drawImage(this.image, this.point.x - this.radius, this.point.y - this.radius, 2*this.radius,2*this.radius,null);
        }
    }

    @Override
    public void animate(Platno p) {
        this.point.x += this.vc;
        if (this.vc > 0) {
            if (this.point.x - this.radius > p.getWidth()) {
                this.point.x = 0 - this.radius;
            }
        } else if (this.point.x + this.radius < 0) {
            this.point.x = p.getWidth() + this.radius;
        }
    }

    @Override
    public void setVC(int vc) {
        if (this.vc + vc > -10 && this.vc + vc < 10) {
            this.vc += vc;
        }
    }

    @Override
    public boolean detect(int curX, int curY) {
        double a = Math.pow(curX - this.point.x, 2);
        double b = Math.pow(curY - this.point.y, 2);
        return Math.sqrt(a + b) <= this.radius;
    }
}
