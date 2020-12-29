/**
 * Phanna Chrin
 * Purpose: This program will create a JFrame with a JPanel from the the ImagePanel
 * class. It will have a menu bar and pass images to the panel object to display. 
 * User can change the images through the menu items.
 * Inputs: N/A
 * Outputs: N/A
 */

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuFrame extends JFrame {
	//Instance fields
	private JMenuBar bar = new JMenuBar();
	private JMenu menu = new JMenu("Choose Image");
	private Image image = new ImageIcon("ball.png").getImage();
	private Image image2 = new ImageIcon("bug.png").getImage();
	private Image image3 = new ImageIcon("jerry.png").getImage();
	private Image nImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
	private Image nImage2 = image2.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
	private Image nImage3 = image3.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
	private ImageIcon icon1 = new ImageIcon(nImage);
	private ImageIcon icon2 = new ImageIcon(nImage2);
	private ImageIcon icon3 = new ImageIcon(nImage3);
	private JMenuItem item1 = new JMenuItem("Soccer Ball", icon1);
	private JMenuItem item2 = new JMenuItem("Lady Bug", icon2);
	private JMenuItem item3 = new JMenuItem("Jerry", icon3);
	private MyMenuListener test = new MyMenuListener();
	private ImagePanel panel = new ImagePanel(image);
	
	//Constructor
	public MenuFrame(){
		setTitle("Click The Image!");
		setSize(600, 600);
		add(panel);
		setResizable(false);
		setJMenuBar(bar);
		item1.addActionListener(test);
		item2.addActionListener(test);
		item3.addActionListener(test);
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		bar.add(menu);
	}
	//Inner Class ActionListener for the menu items
	class MyMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == item1){
				panel.setImage(image);
			}
			else if(e.getSource() == item2){
				panel.setImage(image2);
			}
			else if(e.getSource() == item3){
				panel.setImage(image3);
			}
		}
	}
}
