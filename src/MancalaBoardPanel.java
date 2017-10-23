import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MancalaBoardPanel extends JPanel{

	 MancalaUndo player1;
	 MancalaUndo player2;
	 
     public MancalaBoardPanel(int style, Model data, String colorInside, String colorOutside){
	      JPanel mpp1 = new JPanel();
	      MancalaPit mpc1 = new MancalaPit(150,400,16,data); // Mancala B
	      MancalaPit mpc2 = new MancalaPit(150,400,26,data); // Mancala A
	      MancalaPit mpc15 = new MancalaPit(125,150,15,data); // B6
	      MancalaPit mpc14 = new MancalaPit(125,150,14,data); // B5
	      MancalaPit mpc13 = new MancalaPit(125,150,13,data); // B4
	      MancalaPit mpc12 = new MancalaPit(125,150,12,data); // B3
	      MancalaPit mpc11 = new MancalaPit(125,150,11,data); // B2
	      MancalaPit mpc10 = new MancalaPit(125,150,10,data); // B1
	
	      MancalaPit mpc20 = new MancalaPit(125,150,20,data); // A1
	      MancalaPit mpc21 = new MancalaPit(125,150,21,data); // A2
	      MancalaPit mpc22 = new MancalaPit(125,150,22,data); // A3
	      MancalaPit mpc23 = new MancalaPit(125,150,23,data); // A4
	      MancalaPit mpc24 = new MancalaPit(125,150,24,data); // A5
	      MancalaPit mpc25 = new MancalaPit(125,150,25,data); // A6
	      
	  //pick which strategy to apply
      if(style == 1) 
      {
          mpc1.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc2.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc15.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc14.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc13.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc12.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc11.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc10.setStrategy(new MancalaCustom(colorInside, colorOutside));

          mpc20.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc21.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc22.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc23.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc24.setStrategy(new MancalaCustom(colorInside, colorOutside));
          mpc25.setStrategy(new MancalaCustom(colorInside, colorOutside));
          

      }
      else //0 or -1 (if user didn't select a style)
      {
          mpc1.setStrategy(new MancalaClassic());
          mpc2.setStrategy(new MancalaClassic());
          mpc15.setStrategy(new MancalaClassic());
          mpc14.setStrategy(new MancalaClassic());
          mpc13.setStrategy(new MancalaClassic());
          mpc12.setStrategy(new MancalaClassic());
          mpc11.setStrategy(new MancalaClassic());
          mpc10.setStrategy(new MancalaClassic());

          mpc20.setStrategy(new MancalaClassic());
          mpc21.setStrategy(new MancalaClassic());
          mpc22.setStrategy(new MancalaClassic());
          mpc23.setStrategy(new MancalaClassic());
          mpc24.setStrategy(new MancalaClassic());
          mpc25.setStrategy(new MancalaClassic());
      }
      
      mpp1.add(mpc15);
      mpp1.add(mpc14);
      mpp1.add(mpc13);
      mpp1.add(mpc12);
      mpp1.add(mpc11);
      mpp1.add(mpc10);
      JPanel mpp2 = new JPanel();

      mpp2.add(mpc20);
      mpp2.add(mpc21);
      mpp2.add(mpc22);
      mpp2.add(mpc23);
      mpp2.add(mpc24);
      mpp2.add(mpc25);
      JPanel jp = new JPanel();

      jp.setLayout(new BorderLayout(0,Toolkit.getDefaultToolkit().getScreenSize().height/6)); //center the player label and undo button
      jp.add(mpp1, BorderLayout.NORTH);
      jp.add(mpp2, BorderLayout.SOUTH);
      
      MancalaPlayer mup = new MancalaPlayer(data);
      jp.add(mup, BorderLayout.CENTER);
      JPanel left = new JPanel();
      left.setBorder(new EmptyBorder(Toolkit.getDefaultToolkit().getScreenSize().height/6, 40, 0, 0));
      left.add(mpc1);
      JPanel right = new JPanel();
      right.setBorder(new EmptyBorder(Toolkit.getDefaultToolkit().getScreenSize().height/6, 0, 0, 40)); 
      right.add(mpc2);
      this.setLayout(new BorderLayout());
      this.add(jp, BorderLayout.CENTER);
       
      this.add(left, BorderLayout.WEST);

      this.add(right, BorderLayout.EAST);

      player1 = new MancalaUndo("Player B",data);
      this.add(player1,  BorderLayout.NORTH);   
      player1.add(Box.createHorizontalStrut((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth())); // specific to users monitor width
      
      player2 = new MancalaUndo("Player A",data);
      this.add(player2,  BorderLayout.SOUTH);
      player2.add(Box.createHorizontalStrut((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth())); // specific to users monitor width
   }

   /**
    * paintComponent draws panel
    */
   public void paintComponent(Graphics g, Model data, MancalaBoardPanel player){
      super.paintComponent(g);

   }
}