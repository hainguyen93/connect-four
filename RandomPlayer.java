package uk.ac.sheffield.aca13pn;

import sheffield.EasyReader;

public class RandomPlayer extends Connect4Player {
	
	/**The reader to reader input from the keyboard */
    EasyReader reader;
    	
    /**
     * Constructor.
     */
	public RandomPlayer(){
		reader = new EasyReader();
	}
	
	/**
	 * Enter the column, check column full or not, if not, drop the counter at that column..
	 * If column full: if the whole board full, draw, if not, ask for another column.
	 * @param gameState the state of the game (i.e. board, turn,...)
	 */
	public void makeMove(Connect4GameState gameState){
		int col = enterCol();	
		boolean colFull = gameState.isColumnFull(col);
		
		if (colFull){
			if (!gameState.isBoardFull()){
				do {
					col = enterCol();
					colFull = gameState.isColumnFull(col);
				} while (colFull);
				System.out.println("Computer dropped counter in column "+col);
				dropCounter(col, gameState);			
						
			} else {
				System.out.print("The board is full.");
				
			}
		} else {
			System.out.println("Computer dropped counter in column "+col);
			dropCounter(col, gameState);	
		}
	}
	
	/**
	 * increase the point if player wins or draws
	 * @param point point awarded
	 */
	public void getPoint(int point){
		this.point += point;
	}
	
	/**
	 * Drop the counter at the available column.
	 * @param col the column in which to drop the counter
	 * @param gameState the state of the game (i.e. board, turn,...)
	 */
	public void dropCounter(int col, Connect4GameState gameState ){
		try {
			gameState.move(col);
		} catch (ColumnFullException e){
			e = new ColumnFullException(col);
		} catch (IllegalColumnException e){
			e = new IllegalColumnException(col);
		}		
	}
	
	/**
	 * Randomly choose a column in which to drop the counter
	 * @return the column which has been chosen
	 */
	public int enterCol(){
		return (int)(100*Math.random()) % 7;		
	}	

}
