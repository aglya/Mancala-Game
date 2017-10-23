import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;

public class Model {
   
   public ArrayList<ChangeListener> arrayList;
   HashMap<Integer, Integer> board;
   //allows to go back to previous state of the board
   HashMap<Integer, Integer> cloneboard; 

   private int player;
   private int playerAundo;
   private int playerBundo;

   /**
    * Model constructor initializes the data in the game
    */
   public Model() {
      arrayList = new ArrayList<ChangeListener>();
      board = new HashMap<Integer, Integer>();
      player = 1;
      playerAundo = 3;
      playerBundo = 3;
   }

   /**
    * getBoard returns the Hashmap of the board
    * @return Hashmap of data values of the pits
    */
   public HashMap<Integer, Integer> getBoard() {
      return board;
   }

   public void initializeAllPits(int x) {
      board.put(10, x);
      board.put(11, x);
      board.put(12, x);
      board.put(13, x);
      board.put(14, x);
      board.put(15, x);
      board.put(16, 0);

      board.put(20, x);
      board.put(21, x);
      board.put(22, x);
      board.put(23, x);
      board.put(24, x);
      board.put(25, x);
      board.put(26, 0);
   }

/**
 * 
 * @param pit int hash for which pit the user interacted with
 */
   public void rules(int pit) {
   
      // doesn't move if the pit has no stones
      if (board.get(pit) != 0) {
         // doesn't move on stores
    	 if(pit != 26 || pit != 16){
	         if (player == 1 && pit > 19 && pit < 26 || player == 2 && pit > 9 && pit < 16) { //make sure the player is selecting their own pits
		        	 if(player == 1){
		        		 playerBundo = 3;
		        	 }else{
		        		 playerAundo = 3;
		        	 }
	            cloneboard = new HashMap<Integer, Integer>(board); //store copy for undo
	            int temp = board.get(pit); //get the number of stones in the pit
	            int index = pit + 1; //next pit
	            int lastindex = 0;
	            updatePit(pit, 0); //empty out the current pit
	            for (int i = 0; i < temp; i++) {
	               if (player == 1 && index == 16 || index == 17) 
	                  index = 20; //skip the opponents store and jump to other side of board
	               else if (player == 2 && index == 26 || index == 27)
	                  index = 10; //skip the opponents store and jump to other side of board
	               
	               int temp2 = board.get(index) + 1; //get the number of stones in the next pit and increment the amount
	               updatePit(index, temp2); //add a stone to the next pit
	               lastindex = index;
	               index++;
	            }
	            capture(lastindex); //check if a capture occurred
	            nextTurn(lastindex); //switch player turns if no free turn
	            notifyListeners();
	            endGame(); //check if the game is over
	         } else {
	            JOptionPane.showMessageDialog(null, "It's not your turn!");
	         }
    	 }else{
    		 JOptionPane.showMessageDialog(null, "Cannot move stones out of the store!");
    	 }
      } else {
         JOptionPane.showMessageDialog(null, "This pit does not contain any stones!");
      }
   }

   public void nextTurn(int lastindex) {
      // Next players turn
      if (lastindex != 26 && lastindex != 16) { //if not free turn 
          if (player == 1) {
              player = 2;
           } else {
              player = 1;
           }
      } else{
    	  cloneboard = null; //reset back
    	  if(player == 1){
    		  playerAundo = 3;
    	  }else{
    		  playerBundo = 3;
    	  }
         JOptionPane.showMessageDialog(null, "Free turn!");
         
      }
   }
   
   /**
    * capture method checks if there is anything 
    * to capture and captures opponents stones
    * @param pit index int position of last stone placed
    */
   public void capture(int index) {

       int pitsA = 20;
       int pitsB = 15;
      // capture for player A
      if (player == 1 && index > 19 && index < 26) { //if not in store
         for (int i = 0; i < 6; i++) { //iterate over the pits and check for capture
        	//if there is only one stone in players pit and the opposing pit has stones
            if (index == pitsA && board.get(pitsA) == 1 && board.get(pitsB) != 0) { 
               int stones = board.get(pitsB) + board.get(pitsA); //take all the stones
               updatePit(pitsA, 0);
               updatePit(pitsB, 0);
               updatePit(26, board.get(26) + stones); //put them in the store
               JOptionPane.showMessageDialog(null, "Player A captured!");
            }
            pitsA++;
            pitsB--;
         }
      }
      // capture for player B
      if (player == 2 && index > 9 && index < 16) { //if not in store
         for (int i = 0; i < 6; i++) { //iterate over the pits and check for capture
        	//if there is only one stone in players pit and the opposing pit has stones
            if (index == pitsB && board.get(pitsB) == 1 && board.get(pitsA) != 0) {
               int stones = board.get(pitsB) + board.get(pitsA); //take all the stones
               updatePit(pitsA, 0);
               updatePit(pitsB, 0);
               updatePit(16, board.get(16) + stones); //put them in the store
               JOptionPane.showMessageDialog(null, "Player B captured!");
            }
            pitsA++;
            pitsB--;
         }
      }
   }

   public void endGame() {

      //checks if all pits on any one side are empty
      if (board.get(20) == 0 && board.get(21) == 0 && board.get(22) == 0
            && board.get(23) == 0 && board.get(24) == 0
            && board.get(25) == 0) {
         int temp = board.get(10) + board.get(11) + board.get(12)
         + board.get(13) + board.get(14) + board.get(15);
         updatePit(16, board.get(16) + temp); //update the store with the pits on the side that has stones
         winner();
      }

      if ((board.get(10) == 0 && board.get(11) == 0 && board.get(12) == 0
            && board.get(13) == 0 && board.get(14) == 0 
            && board.get(15) == 0)) {
         int temp = board.get(20) + board.get(21) + board.get(22)
         + board.get(23) + board.get(24) + board.get(25);
         updatePit(26, board.get(26) + temp);  //update the store with the pits on the side that has stones
         winner();
      }
   }

   /**
    * Determines the winner of the game if there 
    * is one. Otherwise, returns a tie.
    */
   public void winner() {

      if (board.get(26) > board.get(16)){
         JOptionPane.showMessageDialog(null, "Player A won! Congratulations!");
         System.exit(0);
      }
      else 
    	  if (board.get(26) < board.get(16)) {
    		  JOptionPane.showMessageDialog(null, "Player B won! Congratulations!");
    		  System.exit(0);
      } else{
         JOptionPane.showMessageDialog(null, "Game tied!");
         System.exit(0);
      }

   }


   public void updatePit(int key, int value) {
      board.put(key, value);
   }


   public int getPitCount(int key) {
      return board.get(key);
   }

   public String getPlayer() {
      if (player == 1)
         return "Player A";
      else
         return "Player B";

   }

   public void undo() {
	   
      if(player == 1){
      if (playerBundo >= 1) {
    	 player = 2; //switch back to other player
    	 playerBundo--;
         board = cloneboard; //copy over the previous board state
         player = 2;
         cloneboard = null; //reset back
         this.notifyListeners();
      } else
         JOptionPane.showMessageDialog(null, "Player B has no undo's left!");

      this.notifyListeners();
      }
      else if (playerAundo >= 1) {
          	player = 1; //switch back to other player
          	playerAundo--;
            board = cloneboard; //copy over the previous board state
            cloneboard = null; //reset back
            this.notifyListeners();
         } else
            JOptionPane.showMessageDialog(null, "Player A has no undo's left!");

         this.notifyListeners();
    }
   
 
   public int getUndoFirst(){
	   return playerAundo;
   }


   public int getUndoSecond(){
	   return playerBundo;
   }

   public void attach(ChangeListener cl) {
      arrayList.add(cl);
   }

   /**
    * notifyListeners notifies when to update the data
    */
   public void notifyListeners() {
      for (ChangeListener x : arrayList) {
         x.stateChanged(null);
      }

   }

}