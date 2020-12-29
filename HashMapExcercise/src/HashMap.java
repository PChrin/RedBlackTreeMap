import java.lang.reflect.Array;
import java.util.ArrayList;

// Implements the Map ADT using a hash table with open addressing and quadratic
// probing.
public class HashMap <KeyType, ValueType> {
	// The hashtable array will be an array of Entry objects. Each Entry stores
	// a key and value.
	private class Entry {
		public KeyType mKey;
		public ValueType mValue;  
	}
	
	// This object will be used to mark NIL (a deleted entry) in the table.
	// You can use == to compare one entry against DELETED_ENTRY to see if the
	// entry has been deleted.
	private final Entry DELETED_ENTRY = new Entry();
	
	// The table itself.
	private Entry[] mTable;
	// The count of how many keys are already in the map.
	private int mCount;
	
	// Constructs an empty hashtable. The size of the table will be the smallest
	// power of 2 that is greater than or equal to the requested size.
	public HashMap(int requestedSize) {
		// The next line is a workaround for Java not liking us making an array
		// of a generic type. (Node is a generic type because it has generic
		// members.)
	      
		// TODO: modify the tableSize variable so it is equal to the smallest 
		// power of 2 that is greater than or equal to requestedSize.
		
		int i = 0;
		int correctedSize;
		
		while((Math.pow(i, 2) < requestedSize)){
			i++;
		}
		correctedSize = (int) Math.pow(i, 2);
		int tableSize = correctedSize;
		mTable = (Entry[])Array.newInstance(Entry.class, tableSize); 
		// mTable's entries are all null initially. null means never assigned.
	}
	
	// Inserts the given key and value into the table, assuming no entry with 
	// an equal key is present. If such an entry is present, override the entry's
	// value.
	public void insert(KeyType key, ValueType value) {
		// Every object in Java has a .hashCode() function that computes a h(k)
		// value for that object. Use that function for your hash table index
		// calculations.
		// Note that a .hashCode() can be negative, but array indices cannot.
		// Use Math.abs to make sure the index is positive.
		mCount = count(mTable);
		double loadLevel = (double)mCount/(double)mTable.length;
		if(loadLevel > 0.8){
			resizeTable(mTable.length * 2);
			int index = getHashIndex(key);
			Entry e = new Entry();
			e.mKey = key;
			e.mValue = value;
			if(mTable[index] == null || mTable[index] == DELETED_ENTRY){
				mTable[index] = e;
			}
			else if(mTable[index].mKey.equals(e.mKey)){
				mTable[index].mValue = e.mValue;
			}
			else{
				int failure = 1;
				index = getHashIndex(key,failure);
				while(mTable[index] != null && mTable[index] != DELETED_ENTRY){
					failure++;
					index = getHashIndex(key,failure);

				}
				mTable[index] = e;
			}
		}
		else{
			int index = getHashIndex(key);
			Entry e = new Entry();
			e.mKey = key;
			e.mValue = value;
			if(mTable[index] == null || mTable[index] == DELETED_ENTRY){
				mTable[index] = e;
			}
			else if(mTable[index].mKey.equals(e.mKey)){
				mTable[index].mValue = e.mValue;
			}
			else{
				int failure = 1;
				index = getHashIndex(key,failure);
				while(mTable[index] != null && mTable[index] != DELETED_ENTRY){
					failure++;
					index = getHashIndex(key,failure);
				}
				mTable[index] = e;
			}
		}
	}
	
	// Returns the value associated with the given key, if it is present.
	// Returns null if the value is not present.
	public ValueType find(KeyType key) {
		// Replace the following line after you write the find method.
		// return null;
		int index = getHashIndex(key);
		Entry e = mTable[index];
		if(e == null){
			throw new NullPointerException("Key is not in the table.");
		}
		else if(e.mKey.equals(key)){
			ValueType value = e.mValue;
			return value;
		}
		else{
			int failure = 1;
			int m = mTable.length;
			index = getHashIndex(key,failure);
			while(mTable[index] != null && failure != m){
				e = mTable[index];
				if(e != DELETED_ENTRY && e.mKey.equals(key)){
					ValueType value = e.mValue;
					return value;
				}
				failure++;
				index = getHashIndex(key,failure);
			}
			throw new NullPointerException("Key is not in the table.");
		}
	}
	   
	// Removes the pair with the given key from the table.
	public void remove(KeyType key) {
		int index = getHashIndex(key);
		if(mTable[index] == null){
			throw new NullPointerException("Key is not in the table.");
		}
		else if(mTable[index].mKey.equals(key)){
			mTable[index] = DELETED_ENTRY;
		}
		else{
			int failure = 1;
			int m = mTable.length;
			index = getHashIndex(key,failure);
			while(mTable[index] != null && failure != m){
				if(mTable[index].mKey.equals(key)){
					mTable[index] = DELETED_ENTRY;
					break;
				}
				failure++;
				index = getHashIndex(key,failure);
			}
		}
	}

	//given a key, return true if the key is in the  map, and false otherwise
	public boolean containsKey(KeyType key){
		int index = getHashIndex(key);
		Entry e = mTable[index];
		if(e == null){
			return false;
		}
		else if(e.mKey.equals(key)){
			return true;
		}
		else{
			int failure = 1;
			index = getHashIndex(key,failure);
			while(mTable[index] != null && failure != mTable.length){
				e = mTable[index];
				if(e.mKey.equals(key)){
					return true;
				}
				failure++;
				index = getHashIndex(key,failure);
			}
			return false;
		}
	}
	
	//returns a list of all the keys in the table
	public ArrayList<String> keySet(){
		ArrayList<String> array = new ArrayList<>(mTable.length);
		for(int i = 0; i < mTable.length; i++){
			if(mTable[i] != null && mTable[i] != DELETED_ENTRY){
				array.add((String) mTable[i].mKey);
			}
		}
		return array;
	}
	
	// You may find it helpful to implement and use these utility methods:
	private int getHashIndex(KeyType key) {
		// returns the index of where this key should be found in the table, WITHOUT
		// probing.
		int m = mTable.length;
		int index = Math.abs(key.hashCode()) % m;
		return index;
	}
	   
	private int getHashIndex(KeyType key, int failures) {
	   // returns the index of where this key should be found in the table, USING
	   // probing with the given number of failures.
	   int m = mTable.length;
	   int p = probe(failures);
	   int index = (Math.abs(key.hashCode()) + p) % m;
	   return index;
	}
	   
	private boolean keyFoundAtIndex(KeyType key, int index) {
	   // returns true if the hash table has an Entry at the given index, and that
	   // entry's key is .equals() to the given key.
	   return false;
	}
	   
	private static int probe(int i) {
		// implement the probing function specified in the assignment.
		i = (int)((Math.pow(i, 2) + i)/2);
		return i;
	}
	   
	private void resizeTable(int newSize) {
		// perform the (expensive) resizing of the table, by creating a new array
		// of the given size, and then inserting each non-deleted Entry in the 
		// current table into the new table. 
		Entry[] temp = (Entry[])Array.newInstance(Entry.class, mTable.length);
		temp = mTable.clone();
		mTable = (Entry[])Array.newInstance(Entry.class, newSize);
		for(int i = 0; i < temp.length; i++){
			if(temp[i] != DELETED_ENTRY && temp[i] != null){
				int index = getHashIndex(temp[i].mKey);
				Entry e = new Entry();
				e.mKey = temp[i].mKey;
				e.mValue = temp[i].mValue;
				if(mTable[index] == null || mTable[index] == DELETED_ENTRY){
					mTable[index] = e;
				}
				else if(mTable[index].mKey.equals(e.mKey)){
					mTable[index].mValue = e.mValue;
				}
				else{
					int failure = 1;
					index = getHashIndex(temp[i].mKey,failure);
					while(mTable[index] != null && mTable[index] != DELETED_ENTRY){
						failure++;
						index = getHashIndex(temp[i].mKey,failure);
					}
					mTable[index] = e;
				}
			}
		}
	}
	
	private int count(Entry[] e){
	//count the number of data elements in the map and return the value
		int count = 0;
		for(int i = 0; i < e.length; i++){
			if(e[i] != null && e[i] != DELETED_ENTRY){
				count++;
			}
		}
		return count;
	}
}
