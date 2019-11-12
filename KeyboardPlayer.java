package uk.ac.sheffield.aca13pn;
import sheffield.*;

public class KeyboardPlayer extends Connect4Player {
	/**The reader to reader input from the keyboard */
	EasyReader reader;
	int point;
	
	/**
     * Constructor.
     */
	public KeyboardPlayer(){
		reader = new EasyReader();
		point = 0;
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
			System.out.println("This column is full.");
			if (!gameState.isBoardFull()){
				do {
					col = enterCol();
					colFull = gameState.isColumnFull(col);
				} while (colFull);
				dropCounter(col, gameState);			
						
			} else {
				System.out.print("The board is full.");
			}
		} else {
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
	 * Enter a column in which to drop the counter
	 * @return the column which has been chosen
	 */
	public int enterCol(){
		int col;
		do {
			col = reader.readInt("Please enter a column number, 0 to 6: ");			
		} while (col < 0 || col > GameState.NUM_COLS-1);
		return col;
	}	
}
