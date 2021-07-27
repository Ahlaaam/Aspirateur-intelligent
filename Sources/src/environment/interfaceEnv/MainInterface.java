package environment.interfaceEnv;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import environment.Environment;
import environment.Manor;
import main.Main;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class MainInterface extends JFrame {

//Attributes
	private JPanel contentPane;
	private ArrayList<Room> tabRoom = new ArrayList<Room>();
	private JLabel lblApprentissage;
	private JLabel lblNbAction;

//Constructor	
	/**
	 * Create the frame.
	 */
	public MainInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel manorRepresentation = new JPanel();
		contentPane.add(manorRepresentation, BorderLayout.CENTER);
		manorRepresentation.setLayout(new GridLayout(5, 5, 1, 1));
		
		for (int indice = 0; indice < 25; ++indice) {
			Room room1 = new Room();
			manorRepresentation.add(room1, indice);
			tabRoom.add(room1);
		}
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JComboBox explorationAlgo = new JComboBox();panel_1.add(explorationAlgo);
		explorationAlgo.addItem(ExplorationAlgo.nonInformedIterativeDeepForSearch);
		explorationAlgo.addItem(ExplorationAlgo.informedAStar);
		
		JLabel lbltextBefor = new JLabel("nobre d'action avant max exploration : ");
		panel_1.add(lbltextBefor);
		
		lblNbAction = new JLabel("0");
		panel_1.add(lblNbAction);
		
		JLabel lblNewLabel = new JLabel("|");
		panel_1.add(lblNewLabel);
		
		lblApprentissage = new JLabel("Appretissage en cour ");
		panel_1.add(lblApprentissage);
		explorationAlgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Environment.getEnvironment().changeAlgo((ExplorationAlgo)explorationAlgo.getSelectedItem());
			}
		});
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
	}

//Method	
	public void update(Manor manor) {
		for (int indice = 0; indice < tabRoom.size(); ++indice){
			tabRoom.get(indice).reset();
			if (manor.getDustAt(indice%5, indice/5)) {
				tabRoom.get(indice).showDust();
			}
			if (manor.getJewelryAt(indice%5, indice/5)) {
				tabRoom.get(indice).showJewelry();
			}
		}
		tabRoom.get(manor.getPosAspiratorX() + manor.getPosAspiratorY()*5).showRobot();
		
		lblNbAction.setText(((Integer) Main.getAgent().getNbActionBeforLearn()).toString());
		
		if (Main.getAgent().getLerninigEnd()) {
			lblApprentissage.setText("Apprentissage terminé.");
		}else {
			lblApprentissage.setText("Apprentissage en cours");
		}
	}
}
