import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		FileInputStream in = null;
		Scanner input = null;
		Scanner reader = null;
		try{	
			in = new FileInputStream("players_homeruns.csv");
			input = new Scanner(System.in);
			reader = new Scanner(in);
			System.out.println("Please enter the size of the table:");
			int tableSize = input.nextInt();
			input.nextLine();
			HashMap<String,Integer> map = new HashMap<String,Integer>(tableSize);
			reader.useDelimiter(",");
			while(reader.hasNext()){
				String string = reader.nextLine();
				String[] tokens = string.split(",");
				String playerName = tokens[0];
				String temp = tokens[1];
				int homeRun = Integer.parseInt(temp);
				map.insert(playerName, homeRun);
			}
			System.out.println("Enter the baseball player name or 'exit' to quit.");
			String choice = input.nextLine();
			while(!choice.equals("exit")){
				System.out.println("Checking to see if " + choice + " is in the table...");
				boolean status = map.containsKey(choice);
				if(status == true){
					System.out.println("Success.");
					int hr = map.find(choice);
					System.out.println("Number of homeruns: " + hr);
				}
				else{
					System.out.println("Error: " + choice + " is not in the table.");
				}
				System.out.println("Enter the baseball player name or 'exit' to quit.");
				choice = input.nextLine();
			}
			System.out.println("Exiting Program.");
			reader.close();
			in.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			reader.close();
			input.close();
		}
	}
}