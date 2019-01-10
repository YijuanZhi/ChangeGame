import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanelA extends JPanel implements MouseListener{
 
   private int w; //width of the panel
   private int h; //height of the panel
   private Image penny, nickel, dime, quarter; 
   private GameModelA model;
 
   public GamePanelA(){
      super();
      setPreferredSize(new Dimension(400, 300));
      addMouseListener(this);
      penny = (Toolkit.getDefaultToolkit()).getImage(getClass().getResource("images/penny.gif"));
      nickel = (Toolkit.getDefaultToolkit()).getImage(getClass().getResource("images/nickel.gif"));
      dime = (Toolkit.getDefaultToolkit()).getImage(getClass().getResource("images/dime.gif"));
      quarter = (Toolkit.getDefaultToolkit()).getImage(getClass().getResource("images/quarter.gif"));
      model = new GameModelA();
   }
 
 //Draws graphics on the panel.
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      w = getWidth();
      h = getHeight();
      setBackground(Color.WHITE);
      g.setColor(Color.BLACK);
      g.drawLine(0, 0, w-1, 0);
      g.drawLine(0, 0, 0, h-1);
      g.drawLine(0, h-1, w-1, h-1);
      g.drawLine(w-1, 0, w-1, h-1);
     
      g.drawLine(w/4, 0, w/4, h);
      g.drawLine(w/2, 0, w/2, h);
      g.drawLine(3*w/4, 0, 3*w/4, h);
      g.drawLine(0, h/3, w, h/3);
      g.drawLine(0, 2*h/3, w, 2*h/3);
     
      int[][] position = model.getCurrentPosition();
     
      for(int i = 0; i < 3; i++){
         for(int j = 0; j < 4; j++){
            if(position[i][j] == 1) g.drawImage(penny, j*w/4+w/8-50, i*h/3+h/6-50, this);
            else if(position[i][j] == 5) g.drawImage(nickel, j*w/4+w/8-50, i*h/3+h/6-50, this);
            else if(position[i][j] == 10) g.drawImage(dime, j*w/4+w/8-50, i*h/3+h/6-50, this);
            else if(position[i][j] == 25) g.drawImage(quarter, j*w/4+w/8-50, i*h/3+h/6-50, this);
         }
      }
   }
 
 //Starts a new game.
   public void newGame(){
      model.initiatePositions();
      repaint();
   }
  
 //Restarts current game.
   public void restartGame(){
      model.setCurrentPosition(model.getStartPosition());
      model.restart();
      repaint();
   }
    
   public void mouseClicked(MouseEvent e){}
          
   public void mouseEntered(MouseEvent e){}
          
   public void mouseExited(MouseEvent e){}
    
 //When mouse button is pressed, checks any adjacent position is empty.
 //If yes, moves the coin there.
   public void mousePressed(MouseEvent e){
      if (e.getButton() == MouseEvent.BUTTON1){
      //detect in what part of the panel the click occurred
         int x = e.getX();
         int y = e.getY();
         int i, j;
      
         if(x < w/4) j = 0;
         else if(x < w/2) j = 1;
         else if(x < 3*w/4) j = 2;
         else j = 3;
      
         if(y < h/3) i = 0;
         else if(y < 2*h/3) i = 1;
         else i = 2;
      
         model.moveCoin(i, j);   
         repaint();
         boolean win = model.checkWin();
         if(win)
            JOptionPane.showMessageDialog(null,"Congratulations! You won!",
               "Change Change", JOptionPane.DEFAULT_OPTION);
      } 
   }  
 
   public void mouseReleased(MouseEvent e){} 
 
   public void previous(){
      int[][]old;
      old=model.getLastPosition();
      model.setCurrentPosition(old);
      model.removeOldHistory();
      repaint();
   }
 
}