/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author pepe
 */
public class asterojd extends asteroid{

    private Image texture;
    
    public asterojd(int x, int y) {
        super(x, y, 80, 80);
        texture = new ImageIcon(getClass().getResource("asteroid.png")).getImage().getScaledInstance(this.bounds.width, this.bounds.height, Image.SCALE_DEFAULT);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(texture, this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height, null);
        g.setColor(Color.black);
        g.drawRect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
    }
    
}
