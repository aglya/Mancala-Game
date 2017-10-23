import java.awt.Color;

public class MancalaClassic implements MancalaStrategy
{

	Color brown = new Color(102,51,0);
	Color dark_brown = new Color(51,25,0);


   public Color getInsidePitColor() {
      return brown;
   }


   public Color getOutsidePitColor() 
   {
      return dark_brown;
   }


   public Color getFontColor() 
   {
      return Color.WHITE;
   }


   public Color getBeadFill() 
   {
      return Color.WHITE;
   }
}