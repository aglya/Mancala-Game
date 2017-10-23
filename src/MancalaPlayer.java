import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MancalaPlayer extends JPanel{
	
   JLabel playerTurn = new JLabel();
   JButton undoButton = new JButton();
   Model mdm;
   
   /**
    * MancalaUndo Panel constructor sets up the button and view of the panel
    * @param mdm MancalaDataModel of the current data model
    */
   public MancalaPlayer(final Model mdm)
   {
      super();
      this.mdm = mdm;
     
      undoButton.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            mdm.undo();
         }
      });
      mdm.attach((new ChangeListener() {

         public void stateChanged(ChangeEvent e) {
            repaint();
         }
      }));
      this.add(playerTurn);

   }

   /**
    * paintComponent method draws the specific players turn and draws undo button
    * @param g: Graphics
    */
   public void paintComponent(Graphics g){
      super.paintComponent(g);

      playerTurn.setText(" " + mdm.getPlayer()+"'s turn.");
      playerTurn.setForeground(Color.BLUE);
      if(mdm.cloneboard != null){ //if a move has been made
    	  
    	  this.add(undoButton);
	      if(playerTurn.getText().contains("A")){
	    		  undoButton.setText("Undos left for Player B: " + mdm.getUndoSecond());
	      }else{

	    		  undoButton.setText("Undos left for Player A: " + mdm.getUndoFirst());
	      }
      }else{
    	  this.remove(undoButton); //otherwise remove the undo option from the board
      }

   }
}