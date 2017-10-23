
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaPit extends JComponent{
   int width;
   int height;
   int hash = 0;
   int index = 0;
   Model m = null;
   
   //default strategy is classic
   MancalaStrategy s = new MancalaClassic();
   public MancalaPit(final Model mdm){
      super();
      m = mdm;
      this.addMouseListener(new MouseAdapter(){
         public void mousePressed(MouseEvent event){
            mdm.rules(hash);
            repaint();
         }
       });
      
      mdm.attach(new ChangeListener(){
    	 @Override
         public void stateChanged(ChangeEvent e) {
    		 repaint();
    	 }
      });
   }

   public MancalaPit(int x, int y, int hash, Model mdm){
      this(mdm);
      this.width = x;
      this.height = y;
      this.hash = hash;
   }
   
   /**
    * setStrategy sets strategy of GUI
    * @param strategy MancalaStrategy 
    */
   public void setStrategy(MancalaStrategy strategy){
      s = strategy;
   }

   /**
    * paintComponent draws pits and stones
    */
   public void paintComponent(Graphics g){
	  
      Dimension preferredSize = new Dimension(width,height);
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      this.setSize(width+1, height+1);
      this.setPreferredSize(preferredSize);
      this.setMaximumSize(preferredSize);
      this.setMinimumSize(preferredSize);
      Rectangle2D rectangle = new Rectangle2D.Double(0,0,width,height);
      g2.draw(rectangle);
      g2.setColor(s.getOutsidePitColor());
      g2.fill(rectangle);
    
      if(hash != 16 && hash != 26){ //Player A and B Mancala pits
	      Ellipse2D ellipse = new Ellipse2D.Double(0,0,width,height);
	      g2.setColor(s.getInsidePitColor());
	      g2.fill(ellipse);
      }

      g2.setColor(s.getFontColor());
      g2.drawString(((Integer)m.getPitCount(hash)).toString(), width/2, height-5); //number of stones in pits
      int column = 0;
      int row = -6;
       for (int z = 0; z < m.getPitCount(hash); z++ ){ //for stones
    	  column += 2; 
    	  if(z%6 == 0){ 
    		  row += z - 1;
    	  }
    	  g2.setColor(s.getBeadFill());
    	  g2.fill(new Ellipse2D.Double(width/5+(row*2),10+(column*6),10,8)); 
      }
     

    }
   
   /**
    * getPitHash method returns position of pit
    * @return int value of the pit
    */
   public int getPitHash(){
      return hash;
   }
}