import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuChoice extends JFrame {
	
	private ImageIcon ball = new ImageIcon("ball.png");
	private ImageIcon bug = new ImageIcon("bug.png");
	private ImageIcon jerry = new ImageIcon("jerry.png");
	private JPanel panel = new JPanel();
	private JLabel test = new JLabel("Please choose what to catch:");
	private JLabel label1 = new JLabel(ball);
	private JLabel label2 = new JLabel(bug);
	private JLabel label3 = new JLabel(jerry);
	private MyMouseListener mouse = new MyMouseListener();
	
	public MenuChoice(){
		this.add(panel);
		this.setTitle("Let's Play!");
		this.panel.setLayout(new BorderLayout());
		this.label1.setName("Ball");
		this.label2.setName("Bug");
		this.label3.setName("jerry");
		this.panel.add(test, BorderLayout.NORTH);
		this.panel.add(label1, BorderLayout.WEST);
		this.panel.add(label2,BorderLayout.CENTER);
		this.panel.add(label3, BorderLayout.EAST);
		this.label1.addMouseListener(mouse);
		this.label2.addMouseListener(mouse);
		this.label3.addMouseListener(mouse);

	}
	
	class MyMouseListener implements MouseListener{
		public void mousePressed(MouseEvent event){
			JLabel test = (JLabel)event.getSource();
			if(test.getName().equals("Ball")){
				//System.out.println("I clicked on soccer ball!");
				JFrame frame = new JFrame();
				frame.setSize(600, 600);
				frame.setTitle("Catch The Soccer Ball!");
		        frame.setResizable(false);
				frame.getContentPane().add(new ImagePanel());
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			else if (test.getName().equals("Bug")){
				//System.out.println("I clicked on lady bug!");
				JFrame frame = new JFrame();
				frame.setSize(600, 600);
				frame.setTitle("Catch The Lady Bug!");
		        frame.setResizable(false);
				frame.getContentPane().add(new ImagePanel2());
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			else if (test.getName().equals("jerry")){
				//System.out.println("I clicked on Jerry");
				JFrame frame = new JFrame();
				frame.setSize(600, 600);
				frame.setTitle("Catch Jerry!");
		        frame.setResizable(false);
				frame.getContentPane().add(new ImagePanel3());
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			
		}
		public void mouseReleased(MouseEvent event) {}
		public void mouseClicked(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
	}
}
