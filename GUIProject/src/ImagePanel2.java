import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel2 extends JPanel {
	private int x;
	private int y;
	private int xSize = 100;
	private int ySize = 100;
	private int xMov = 460;
	private int yMov = 460;
	private int score;
	private ImageIcon bug = new ImageIcon("bug.png");
	private Image image = bug.getImage();
	private Random rand = new Random();
	private Timer timer = new Timer(500, new TimeListener());
	private Timer timer2 = new Timer(350, new TimeListener());
	private Timer timer3 = new Timer(1, new TimeListener());
	private MyMouseListener mouse = new MyMouseListener();
	
	public ImagePanel2(){
		this.addMouseListener(mouse);
		timer.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawString("Score: " + score, 535, 15);
        if(score < 10){
        	g.drawImage(image, x, y, xSize, ySize, this);
        }
        else{
        	xSize = 50;
        	ySize = 50;
        	xMov = 525;
        	yMov = 525;
        	g.drawImage(image, x, y, xSize, ySize, this);
        }
	}
	
	class TimeListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			x = rand.nextInt(xMov);
			y = rand.nextInt(yMov);
			repaint();
		}
	}
	
	class MyMouseListener implements MouseListener{
		public void mousePressed(MouseEvent event) {
			Rectangle box = new Rectangle(x,y,xSize,ySize);
			if(box.contains(event.getPoint())){
				//System.out.println("You clicked on the image!");
				score++;
				//System.out.println("Score: " + score);
				if(score == 5){
					timer.stop();
					timer2.start();
				}
				if(score == 10){
					timer2.stop();
					timer3.start();
				}
				
			}
			
		}
		public void mouseReleased(MouseEvent event) {}
		public void mouseClicked(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
	}
	
}
