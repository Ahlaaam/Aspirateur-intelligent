package environment.interfaceEnv;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;

public class Room extends JPanel {

//Attributes	
	private BufferedImage robotPicture;
	private BufferedImage dustPicture;
	private BufferedImage jewelryPicture;
	private PicturePanel picturePanelRobot;
	private PicturePanel picturePanelJewelry;
	private PicturePanel picturePanelDust;
	
//Construtor	
	/**
	 * Create the panel.
	 */
	public Room() {
		try {
			robotPicture = ImageIO.read(new File("./ressources/robot.png"));
		} catch (IOException e) {
			System.out.println("There is an error in reading the robot image.");
			e.printStackTrace();
		}
		try {
			dustPicture = ImageIO.read(new File("./ressources/dust.png"));
		} catch (IOException e) {
			System.out.println("There is an error in reading the dust image.");
			e.printStackTrace();
		}
		try {
			jewelryPicture = ImageIO.read(new File("./ressources/jewelry.png"));
		} catch (IOException e) {
			System.out.println("There is an error in reading the jewelry image.");
			e.printStackTrace();
		}
		
		setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 60, 120);
		panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(panel1);
		panel1.setLayout(null);
		
		picturePanelDust = new PicturePanel(dustPicture);
		picturePanelDust.setBounds(0, 60, 60, 60);
		panel1.add(picturePanelDust);
		
		picturePanelJewelry = new PicturePanel(jewelryPicture);
		picturePanelJewelry.setBounds(0, 0, 60, 60);
		panel1.add(picturePanelJewelry);
				
		picturePanelRobot = new PicturePanel(robotPicture);
		picturePanelRobot.setBounds(60, 0, 60, 120);
		add(picturePanelRobot);
		picturePanelRobot.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
	}
	
//Methods
	/**
	 * Hide the element (jewelry, dust, robot)
	 */
	public void reset() {
		picturePanelRobot.setVisible(false);
		picturePanelJewelry.setVisible(false);
		picturePanelDust.setVisible(false);
	}
	
	/**
	 * Show the jewelry
	 */
	public void showJewelry() {
		picturePanelJewelry.setVisible(true);
	}
	
	/**
	 * show the dust
	 */
	public void showDust() {
		picturePanelDust.setVisible(true);
	}
	
	/**
	 * Show the robot 
	 */
	public void showRobot() {
		picturePanelRobot.setVisible(true);
	}
}
