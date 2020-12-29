/**
 * Phanna Chrin
 * Purpose: The purpose of this program is to allow the user to read the
 * content of a memo file.
 * Inputs: The user can decide if he/she wants to continue to read memos.
 * Outputs: Content of the memo such as topic, date created, and body of memo.
 */

import java.util.*;
import javax.swing.JFileChooser;
import java.io.*;

public class MemoReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Scanner console = new Scanner(System.in);
			JFileChooser chooser = new JFileChooser();

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				File selectedFile = chooser.getSelectedFile();	
				Scanner in = new Scanner(selectedFile);
				boolean isFinish = false;
		   
				while(in.hasNextLine() && !isFinish){
					String memoTopic = in.nextLine();
					String date = in.nextLine();
					String memoText = in.nextLine();
					System.out.println(memoTopic);
					System.out.println(date);
					System.out.println(memoText);
					System.out.println("Do you want to see the next memo? Type 'y' for yes "
							+ "or 'n' for no:");
					String choice = console.next();
					while(!choice.equals("y") && !choice.equals("n")){
						System.out.println("Please type 'y' for yes of 'n' for no:");
						choice = console.next();
					}
					if(in.hasNextLine()){
						if(choice.equals("n")){
							isFinish = true;
							System.out.println("Closing memo.");
						}
					}
					else{
						System.out.println("There are no more memos.");
					}
				}
				in.close();
				console.close();
			}
		}
		catch(IOException except){
			except.printStackTrace(System.out);
		}
	}
}
