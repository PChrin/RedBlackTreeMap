/**
 * Phanna Chrin
 * Purpose: This program allows the user to create a JPanel object
 * that accepts images and move it around the panel at certain interval.
 * It will also keep track of how many times the user mouse click on the image.
 * Inputs: N/A
 * Outputs: N/A
 */

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	//Instance fields
	private int x;
	private int y;
	private int xSize = 100;
	private int ySize = 100;
	private int xMov = 450;
	private int yMov = 450;
	private int score;
	private Image image;
	private Random rand = new Random();
	private Timer timer = new Timer(750, new TimeListener());
	private Timer timer2 = new Timer(500, new TimeListener());
	private MyMouseListener mouse = new MyMouseListener();
	private JLabel test = new JLabel("Score:" + score);
	
	//Default constructor
	public ImagePanel(){
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(test);
		this.addMouseListener(mouse);
		timer.start();
	}
	//Constructor with image parameter
	public ImagePanel(Image a){
		this.image = a;
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(test);
		this.addMouseListener(mouse);
		timer.start();
	}
	//Method to set image
	public void setImage(Image i){
		this.image = i;
	}
	//Override paintComponent method
	public void paintComponent(Graphics g){
		super.paintComponents(g);
        if(score < 10){
        	g.drawImage(image, x, y, xSize, ySize, this);
        }
        else{
        	xSize = 50;
        	ySize = 50;
        	xMov = 505;
        	yMov = 505;
        	g.drawImage(image, x, y, xSize, ySize, this);
        }
	}
	//Inner class TimeListener to move image randomly
	class TimeListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			x = rand.nextInt(xMov);
			y = rand.nextInt(yMov);
			repaint();
		}
	}
	//Inner class MyMouseListener to find mouse click
	class MyMouseListener implements MouseListener{
		public void mousePressed(MouseEvent event) {
			Rectangle box = new Rectangle(x,y,xSize,ySize);
			if(box.contains(event.getPoint())){
				score++;
				test.setText("Score: " + score);
				if(score == 5){
					timer.stop();
					timer2.start();
				}
			}
		}
		public void mouseReleased(MouseEvent event) {}
		public void mouseClicked(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
	}
	
}
