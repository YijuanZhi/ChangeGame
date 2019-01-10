import java.awt.event.*;
import javax.swing.*;

public class GameMenuA extends JMenuBar{

   private GamePanelA panel;
   private JMenuItem newGame, restart, goBack, exit;

   public GameMenuA(GamePanelA p){
      panel = p;
      JMenu menu = new JMenu("Game");
      menu.setMnemonic('G');
      MenuAction menuAction = new MenuAction();
      newGame = new JMenuItem("New Game");
      newGame.setMnemonic('N');
      newGame.addActionListener(menuAction);
      goBack = new JMenuItem("Back Up");
      goBack.addActionListener(menuAction);
      restart = new JMenuItem("Restart This Game");
      restart.setMnemonic('R');
      restart.addActionListener(menuAction);
      exit = new JMenuItem("Exit");
      exit.setMnemonic('x');
      exit.addActionListener(menuAction);
      menu.add(newGame);
      menu.add(restart);
      menu.add(goBack);
      menu.addSeparator();
      menu.add(exit);
      add(menu);
   }

   // Action listener for the menu
   private class MenuAction implements ActionListener{   
      public void actionPerformed(ActionEvent e){
         JMenuItem m = (JMenuItem)e.getSource();
         if (m == newGame)
            panel.newGame();      
         else if (m == restart)
            panel.restartGame(); 
         else if (m == goBack){panel.previous();}
         else if (m == exit)
            System.exit(0);
      }
   }
}