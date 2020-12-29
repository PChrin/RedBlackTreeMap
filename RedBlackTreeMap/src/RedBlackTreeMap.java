
// A Map ADT structure using a red-black tree, where keys must implement
// Comparable.
public class RedBlackTreeMap<TKey extends Comparable<TKey>, TValue> {
	// A Node class.
	private class Node {
		private TKey mKey;
		private TValue mValue;
		private Node mParent;
		private Node mLeft;
		private Node mRight;
		private boolean mIsRed;
		
		public Node(TKey key, TValue data, boolean isRed) {
			mKey = key;
			mValue = data;
			mIsRed = isRed;
			mLeft = NIL_NODE;
			mRight = NIL_NODE;
		}
		@Override
		public String toString() {
			return "(" + mKey + ", " + mValue + ")";
		}
	}
	
	private Node mRoot;
	private int mCount;
	
	// Rather than create a "blank" black Node for each NIL, we use one shared
	// node for all NIL leaves.
	private final Node NIL_NODE = new Node(null, null, false);
		
	// Get the # of keys in the tree.
	public int getCount() {
		return mCount;
	}
	
	// Finds the value associated with the given key.
	public TValue find(TKey key) {
		Node n = bstFind(key, mRoot); // find the Node containing the key if any
		if (n == null || n == NIL_NODE)
			throw new RuntimeException("Key not found");
		return n.mValue;
	}
		
	// Inserts a key/value pair into the tree, updating the red/black balance
	// of nodes as necessary. Starts with a normal BST insert, then adjusts.
	public void insert(TKey key, TValue data) {
		Node n = new Node(key, data, true); // nodes start red
		
		// normal BST insert; n will be placed into its initial position.
		// returns false if an existing node was updated (no rebalancing needed)
		boolean insertedNew = bstInsert(n, mRoot); 
		if (!insertedNew){
			return;
		}
		// check cases 1-5 for balance violations.
		checkBalance(n);
	}
   
   // Applies rules 1-5 to check the balance of a tree with newly inserted
   // node n.  
   private void checkBalance(Node n) {
      if (n == mRoot) {
			// case 1: new node is root.
			n.mIsRed = false;
	  }
      // handle additional insert cases here.
      //Case 2: Parent node is black.
      else if (n.mParent.mIsRed == false){}
      //Case 3: Parent node and uncle node are both red.
      else if (getUncle(n).mIsRed == true){
    	  n.mParent.mIsRed = false;
    	  getUncle(n).mIsRed = false;
    	  getGrandparent(n).mIsRed = true;
    	  checkBalance(getGrandparent(n));  
   	  }
      //Case 4: Node n is the lr or rl grand child.
      else if ((n.mKey == (n.mParent.mLeft.mKey)) && (n.mParent.mKey == (getGrandparent(n).mRight.mKey))){
    	  singleRotateRight(n.mParent);
    	  singleRotateLeft(n.mParent);
    	  n.mIsRed = false;
    	  n.mLeft.mIsRed = true;
      }
      else if ((n.mKey == (n.mParent.mRight.mKey)) && (n.mParent.mKey == (getGrandparent(n).mLeft.mKey))){
    	  singleRotateLeft(n.mParent);
    	  singleRotateRight(n.mParent);
    	  n.mIsRed = false;
    	  n.mRight.mIsRed = true;
      }
      //Case 5: Node n is the ll or rr grand child.
      else if (n.mKey == n.mParent.mRight.mKey && n.mParent.mKey == getGrandparent(n).mRight.mKey){
    	  singleRotateLeft(n.mParent.mParent);
    	  n.mParent.mIsRed = false;
    	  n.mParent.mLeft.mIsRed = true;
      }
      else if (n.mKey == n.mParent.mLeft.mKey && n.mParent.mKey == getGrandparent(n).mLeft.mKey){
    	  singleRotateRight(n.mParent.mParent);
    	  n.mParent.mIsRed = false;
    	  n.mParent.mRight.mIsRed = true;
      }
   }
	
	// Returns true if the given key is in the tree.
	public boolean containsKey(TKey key) {
		// TODO: using at most three lines of code, finish this method.
		// HINT: write the bstFind method first.
		if(key == bstFind(key,mRoot).mKey){
			return true;
		}
		return false;
	}
	
	// Prints a pre-order traversal of the tree's nodes, printing the key, value,
    // and color of each node.
	public void printStructure() {
    // TODO: a pre-order traversal. Will need recursion.
		if(this.mRoot != null){
			helper(mRoot);
		}
	}
	
	//Helper method for printStructure method.
	private void helper(Node n){
		System.out.print(n.mKey + " :  " + n.mValue + " ");
		if(n.mIsRed == false){
			System.out.println("(Black)");
		}
		else{
			System.out.println("(Red)");
		}
		if(n.mLeft != null){
			helper(n.mLeft);
		}
		if(n.mRight != null){
			helper(n.mRight);
		}
	}
	
	// Returns the Node containing the given key. Recursive.
	private Node bstFind(TKey key, Node currentNode) {
		// TODO: write this method. Given a key to find and a node to start at,
		// proceed left/right from the current node until finding a node whose 
		// key is equal to the given key.
		int compare = key.compareTo(currentNode.mKey);
		if (compare > 0) {
			// key is larger than current node key so go right.
			if (currentNode.mRight != NIL_NODE)
				return bstFind(key, currentNode.mRight);
			else {
				throw new RuntimeException("Key not found.");
			}
		}
		else if (compare < 0) {
			// key is smaller than current node key so go left.
			if (currentNode.mLeft != NIL_NODE)
				return bstFind(key, currentNode.mLeft);
			else {
				throw new RuntimeException("Key not found.");
			}
		}
		else{
			return currentNode;
		}
	}
		
	// Gets the grandparent of n.
	private Node getGrandparent(Node n) {
		// TODO: return the grandparent of n
		if(n.mParent.mParent != null){
			Node gP = n.mParent.mParent;
			return gP;
		}
		else{
			return null;
		}
	}
	
	// Gets the uncle (parent's sibling) of n.
	private Node getUncle(Node n) {
		// TODO: return the uncle of n
		Node gP = getGrandparent(n);
		if(gP == null){
			return null;
		}
		else if(gP.mLeft == n.mParent){
			return gP.mRight;
		}
		else{
			return gP.mLeft;
		}
	}
	
	// Rotate the tree right at the given node.
	private void singleRotateRight(Node n) {
		Node l = n.mLeft, lr = l.mRight, p = n.mParent;
		n.mLeft = lr;
		lr.mParent = n;
		l.mRight = n;
		if (n == mRoot) {
			mRoot = l;
			l.mParent = null;
		}
		else if (p.mLeft == n) {
			p.mLeft = l;
			l.mParent = p;
		}
		else {
			p.mRight = l;
			l.mParent = p;
		}
		n.mParent = l;
	}
	
	// Rotate the tree left at the given node.
	private void singleRotateLeft(Node n) {
		// TODO: do a single left rotation (AVL tree calls this a "rr" rotation)
		// at n.
		Node l = n.mRight, lr = l.mLeft, p = n.mParent;
		n.mRight = lr;
		lr.mParent = n;
		l.mLeft = n;
		if (n == mRoot) {
			mRoot = l;
			l.mParent = null;
		}
		else if (p.mRight == n) {
			p.mRight = l;
			l.mParent = p;
		}
		else {
			p.mLeft = l;
			l.mParent = p;
		}
		n.mParent = l;
	}
	
	// Inserts the key/value into the BST, and returns true if the key wasn't 
	// previously in the tree.
	private boolean bstInsert(Node newNode, Node currentNode) {
		if (mRoot == null) {
			// case 1
			mRoot = newNode;
			mCount++;
			return true;
		}
		else{
			int compare = currentNode.mKey.compareTo(newNode.mKey);
			if (compare < 0) {
			// newNode is larger; go right.
				if (currentNode.mRight != NIL_NODE)
					return bstInsert(newNode, currentNode.mRight);
				else {
					currentNode.mRight = newNode;
					newNode.mParent = currentNode;
					mCount++;
					return true;
				}
			}
			else if (compare > 0) {
				if (currentNode.mLeft != NIL_NODE)
					return bstInsert(newNode, currentNode.mLeft);
				else {
					currentNode.mLeft = newNode;
					newNode.mParent = currentNode;
					mCount++;
					return true;
				}
			}
			else {
				// found a node with the given key; update value.
				currentNode.mValue = newNode.mValue;
				return false; // did NOT insert a new node.
			}
		}
	}
}
