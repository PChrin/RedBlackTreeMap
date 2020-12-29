
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedBlackTreeMap<String, Integer> redBlackTree = new RedBlackTreeMap<>();
		
		//Insert the first 10 data from the .csv file.
		redBlackTree.insert("Babe Ruth", 714);
		redBlackTree.insert("Barry Bonds", 762);
		redBlackTree.insert("Willie Mays", 660);
		redBlackTree.insert("Ty Cobb", 117);
		redBlackTree.insert("Honus Wagner", 101);
		redBlackTree.insert("Hank Aaron", 755);
		redBlackTree.insert("Tris Speaker", 117);
		redBlackTree.insert("Ted Williams", 521);

		redBlackTree.insert("Rogers Hornsby", 301);

		redBlackTree.insert("Stan Musial", 475);
		redBlackTree.printStructure();

				
		/*System.out.println();
		System.out.println("Find method called on Babe Ruth, Honus Wagner, Rogers Hornsby, Stan Musial respectively.");
		//Find one key that is a leaf (has two NIL children).
		System.out.println(redBlackTree.find("Babe Ruth"));
		//Find one key that is a root.
		System.out.println(redBlackTree.find("Honus Wagner"));
		//Find one key that has one NIL child and one non NIL child.
		System.out.println(redBlackTree.find("Rogers Hornsby"));
		//Find one key that is a red node.
		System.out.println(redBlackTree.find("Stan Musial"));*/
		
		/**FileInputStream in = null;
		Scanner reader = null;
		
		try{
			in = new FileInputStream("players_homeruns.csv");
			reader = new Scanner(in);
			reader.useDelimiter(",");
			while(reader.hasNext()){
				String string = reader.nextLine();
				String[] tokens = string.split(",");
				String playerName = tokens[0];
				String temp = tokens[1];
				int homeRun = Integer.parseInt(temp);
				redBlackTree.insert(playerName, homeRun);
			}
			redBlackTree.printStructure();
			
			System.out.println();
			System.out.println("Find method called on Babe Ruth, Honus Wagner, Rogers Hornsby, Stan Musial respectively.");
			//Find one key that is a leaf (has two NIL children).
			System.out.println(redBlackTree.find("Babe Ruth"));
			//Find one key that is a root.
			System.out.println(redBlackTree.find("Honus Wagner"));
			//Find one key that has one NIL child and one non NIL child.
			System.out.println(redBlackTree.find("Rogers Hornsby"));
			//Find one key that is a red node.
			System.out.println(redBlackTree.find("Stan Musial"));			
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			reader.close();
		}*/
	}
}
