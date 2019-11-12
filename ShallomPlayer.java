/**
* 
* Class RandomPlayer
* This class represents the computer player in the connect4 game.
* it inputs into a column at random
* it implements the makeMove method in the Connect4Player class
* @author Shallom Igbre 
* First version 24/03/2014
* 
*/

package uk.ac.sheffield.aca13pn;

import java.util.Random;

public class ShallomPlayer extends Connect4Player {
		
	
	public void getPoint(int point){
		this.point += point;
	}
	
	@Override
    public void makeMove(Connect4GameState gameState)
    {
		/**prompts the computer to input into a column at random*/
	 Random random = new Random();
	 try{
	 int input = random.nextInt(Connect4GameState.NUM_COLS-1);
	 gameState.move(input);
	 /** catch exceptions*/
	 }catch (IllegalColumnException e){
		 
	 }catch(IllegalRowException e){
	 
	 } catch (ColumnFullException e) {
		// TODO Auto-generated catch block
		
	}
		 
	 
}
	
}	