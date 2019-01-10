import java.util.ArrayList;
import java.util.Arrays;

public class GameModelA {
	
   private int[][] currentPosition; //each coin is represented by its value, empty cell is 0
   private int[][] startPosition;
   private int[][] tempPosition;
   private ArrayList<int[][]> history=new ArrayList<int[][]>();
   private int size;
  	
   public GameModelA(){
      currentPosition = new int[3][4];
      startPosition = new int[3][4];
      initiatePositions();
   }
  	
	//Returns the starting position of current game.
   public int[][] getStartPosition(){
      int[][]arr = new int[3][4];
      for(int i = 0; i < 3; i++)
         for(int j = 0; j < 4; j++)
            arr[i][j] = startPosition[i][j];
      return arr;		
   }
	
	//Sets current position to given one.
   public void setCurrentPosition(int[][] arr){
      currentPosition = arr;
   }
	
	//Returns current game position.
   public int[][] getCurrentPosition(){
      return currentPosition;
   }
  	
  //Creates a random starting position.
   public void initiatePositions(){
      history.clear();
      ArrayList<Integer> coins = 
         new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 5, 5, 5, 5, 10, 10, 25));
      int count = 12;
      for(int i = 0; i < 3; i++){
         for(int j = 0; j < 4; j++){
            int temp = (int)(Math.random()*count);
            startPosition[i][j] = coins.get(temp);
            currentPosition[i][j] = coins.get(temp);
            coins.remove(temp);
            count--;
         }
      }
      recordMove();
   }
	
	//Checks whether you can move the coin you clicked on
	//and if yes moves it.
   public void moveCoin(int i, int j){
   	//check the position on top
      if(i-1>=0 && currentPosition[i-1][j]==0){ 			
         currentPosition[i-1][j] = currentPosition[i][j];
         currentPosition[i][j] = 0;
         recordMove();
      }
      //bottom
      else if(i+1<3 && currentPosition[i+1][j]==0){ 			
         currentPosition[i+1][j] = currentPosition[i][j];
         currentPosition[i][j] = 0;
         recordMove();
      }
      //left
      else if(j-1>=0 && currentPosition[i][j-1]==0){ 			
         currentPosition[i][j-1] = currentPosition[i][j];
         currentPosition[i][j] = 0;
         recordMove();
      }
      //right
      else if(j+1<4 && currentPosition[i][j+1]==0){ 			
         currentPosition[i][j+1] = currentPosition[i][j];
         currentPosition[i][j] = 0;
         recordMove();
      }
   }
	
	//Checks if current position is winning (i.e. symmetrical).
   public boolean checkWin(){
      boolean symmetric = true;
      for(int j = 0; j < 4; j++)
         if(currentPosition[0][j] != currentPosition[2][j])
            symmetric = false;
      return symmetric;		
   }
   
   private void recordMove(){
      history.add(copy(this.currentPosition));
   }
   
   public void back(){
      if(history.size()>1){
         setCurrentPosition(history.get(history.size()-2));
         int lastIndex=history.size()-1;
         history.remove(lastIndex);
      }
   }
   
   public void restart(){
      history.clear();
      history.add(startPosition);
   }
   
   public int[][] getLastPosition(){
      int index=history.size()-2;
      return history.get(index);
   }
   
   public void removeOldHistory(){
      int lastIndex=history.size()-1;
      history.remove(lastIndex);
   }
   
   public int getArrayListSize(){
      return history.size();
   }
   
   private int[][] copy(int[][] pos){
      int[][] clone=new int[3][4];
      for(int i=0;i<3;i++){
         for(int j=0;j<4;j++){
            clone[i][j]=pos[i][j];
         }
      }
      return clone;
   }
   
}
