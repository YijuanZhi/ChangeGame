import java.awt.event.*;

public class ChangeGameDriverNew{
   public static void main(String[] args){
      DisplayWindow d = new DisplayWindow();
      GamePanelA p = new GamePanelA();
      d.addPanel(p);
      d.setJMenuBar(new GameMenuA(p));
      d.showFrame();
      int[] abc=new int[5];
      System.out.println(abc[3]);
   }
}