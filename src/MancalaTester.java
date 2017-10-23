
import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MancalaTester {
	
   public static final String[] stones = {"3", "4"}; //can add more or less if needed
   public static final String[] strategy = {"Classic", "Custom"};
   public static final String[] colors = {"Blue", "Gray", "Green", "Orange", "Red", "Yellow"};
   static MancalaFrame frame = new MancalaFrame();
	  
   public static void main(String[] args){	   
      beginPlay();
   }
   
   public static void beginPlay(){
	   
		  Model model = new Model();
	      JFrame inputFrame = new JFrame();
	      String numStones = (String) JOptionPane.
	            showInputDialog(inputFrame, "How many stones would you like to play with?", "Number of Stones", JOptionPane.QUESTION_MESSAGE, null, stones, stones[0]);
	      if(numStones == null) return;
	    	  model.initializeAllPits(Integer.parseInt(numStones)); //put the correct number of stones in the pits
	    	  

	      int style = JOptionPane.showOptionDialog(null,
	            "Please choose a style:",
	            "Style Selection",
	            JOptionPane.DEFAULT_OPTION,
	            JOptionPane.PLAIN_MESSAGE, 
	            null,
	            strategy,
	            strategy[0]);
	      

	      int colorInside = 0;
	      int colorOutside = 0;
	      
	      if(style == 1){
	    	  colorInside = JOptionPane.showOptionDialog(null,
	  	            "Please choose a color for the inside of the pits:",
	  	            "Style Selection",
	  	            JOptionPane.DEFAULT_OPTION,
	  	            JOptionPane.PLAIN_MESSAGE, 
	  	            null,
	  	            colors,
	  	            colors[0]);
	    	  
	    	  colorOutside = JOptionPane.showOptionDialog(null,
		  	            "Please choose a color for the outside of the pits:",
		  	            "Style Selection",
		  	            JOptionPane.DEFAULT_OPTION,
		  	            JOptionPane.PLAIN_MESSAGE, 
		  	            null,
		  	            colors,
		  	            colors[0]);
	      }
	      //user didn't make a selection
	      if(colorInside == -1)
	    	  colorInside = 0;
	      if(colorOutside == -1)
	    	  colorOutside = 0;
	      Container content = frame.getContentPane();
	      MancalaBoardPanel mbp = new MancalaBoardPanel(style, model, colors[colorInside], colors[colorOutside]);
	      content.add(mbp);
	      
	      frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
	      frame.setResizable(false);

	      frame.setTitle("Mancala");

	      frame.setVisible(true);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

}