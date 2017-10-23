import java.awt.Color;

import javax.swing.text.html.StyleSheet;


public class MancalaCustom implements MancalaStrategy
{
	 private StyleSheet style = new StyleSheet();
	 private String insideColor;
	 private String outsideColor;
	 
   public MancalaCustom(String insideColor, String outsideColor){
	   this.insideColor = insideColor;
	   this.outsideColor = outsideColor;
   }
   public Color getInsidePitColor() {
      return style.stringToColor(insideColor.toUpperCase()); 
   }

   public Color getOutsidePitColor() {
      return style.stringToColor(outsideColor.toUpperCase()); 
   }

   public Color getFontColor() {
      return Color.WHITE;
   }

   public Color getBeadFill() {
      return Color.LIGHT_GRAY;
   }
}