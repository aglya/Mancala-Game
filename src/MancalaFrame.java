import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class MancalaFrame extends JFrame{
   /**
    * MancalaFrame constructor that sets up the size of the frame
    */
	public MancalaFrame(){
		super();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height); // scales to your monitor
	}
	
	/**
	 * PaintComponent method draws
	 * @param g Graphics
	 */
	public void paintComponent(Graphics g){
		super.paintComponents(g);
	}
}