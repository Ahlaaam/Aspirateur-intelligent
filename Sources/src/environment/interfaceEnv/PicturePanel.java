package environment.interfaceEnv;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class PicturePanel extends JPanel {

//Attribute
	private Image image = null;

//Constructor	
	public PicturePanel(Image image) {
        this.image = image;
	}

//Setter & getter
    public void setImage(Image image){
        this.image = image;
    }
    
    public Image getImage(Image image){
        return image;
    }
 
//Method
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); //paint background
        int height = this.getSize().height;
        int width = this.getSize().width;
        //g.drawImage(image, 0, 0, this); //use image size          
        graphics.drawImage(image,0,0, width, height, this);
    } 

}
