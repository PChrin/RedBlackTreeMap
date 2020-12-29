/**
 * Phanna Chrin
 * Purpose: The purpose of this program is to allow the user to create a 
 * memo and write to it.
 * Inputs: The user's choice of memo topic and content of memo.
 * Outputs: Memo will write to a text file call memo.txt.
 */

import java.util.*;
import java.io.*;

public class MemoWriter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Scanner read = new Scanner(System.in);
			PrintWriter memo = new PrintWriter("memo.txt");
			boolean isFinish = false;
			
			while(!isFinish){
				System.out.println("Enter topic of memo or 'end' to stop:");
				String topic = read.nextLine();
				if(topic.equals("end")){
					isFinish = true;
				}
				else{
					Date date = new Date();
					memo.println(topic);
					memo.println(date.toString());
					System.out.println("Enter body of memo:");
					String body = read.nextLine();
					memo.println(body);
				}
			}
			System.out.println("Closing memo.");
			read.close();
			memo.close();
		}
		catch(IOException except){
			except.printStackTrace(System.out);
		}
	}

}
