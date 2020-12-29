/**
 * Phanna Chrin
 * Purpose: The purpose of this program is to allow users to create
 * a game confined in a JFrame object where they click on a moving image.
 * At 5 successful mouse clicks the image will move faster and at 10 successful
 * mouse clicks the image will shrink.
 * Inputs: The input will be received in the form of mouse clicks on the images
 * and menu bar.
 * Outputs: A JFrame containing a JPanel with an image moving randomly.
 */

import javax.swing.JFrame;

public class StartGame {
	
	public static void main(String[] args) {
		//Create Java Frame
		MenuFrame frame = new MenuFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
}
