import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaUndo extends JPanel{
  // private String tmp;
   private JTextField player;

   /**
    * MancalaPlayerPanel constructor initializes values
    * @param name of player
    * @param mdm data model of the game
    */
   public MancalaUndo(String name, Model mdm){
      super();
      
      player = new JTextField(name);

      this.add(player);
      player.setEditable(false);

      mdm.attach(new ChangeListener() {
    	  @Override
         public void stateChanged(ChangeEvent e) {
    		  repaint();         
      }});
      
   }

   public void paintComponent(Graphics g, MancalaBoardPanel player){
      super.paintComponent(g);
   }
}