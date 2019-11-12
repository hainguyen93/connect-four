package uk.ac.sheffield.aca13pn;
import sheffield.EasyReader;

/**
 * Code for Intelligent player
 * @author Phan Trung Hai Nguyen
 */

public class IntelligentPlayer extends Connect4Player {
	 	
	/**The reader to reader input from the keyboard */
    EasyReader reader;
    int depth;
            
    /**
     * Constructor.
     */
	public IntelligentPlayer(int depth){
		reader = new EasyReader();
		this.depth = depth;
	}
	
	/**
	 * Enter the column, check column full or not, if not, drop the counter at that column..
	 * If column full: if the whole board full, draw, if not, ask for another column.
	 * @param gameState the state of the game (i.e. board, turn,...)
	 */
	
	public void makeMove(Connect4GameState gameState){
		
		//choose the column to drop counter
		int bestCol = chooseMove((GameState) gameState);	
		System.out.println("Computer dropped counter in column " + bestCol);
		
		//drop counter
		dropCounter(bestCol, (GameState) gameState);	
		
		
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
	public void dropCounter(int col, GameState gameState ){
		try {
			gameState.move(col);
		} catch (ColumnFullException e){
			e = new ColumnFullException(col);
		} catch (IllegalColumnException e){
			e = new IllegalColumnException(col);
		}		
	}
		
	/**
	 * Measure value for Evaluation function 
	 * if the cell holds RED counter, YELLOW counter or EMPTY, 
	 * add the corresponding value to variable in table to value.
	 * @param gameState the current state of the game  
	 * @return value of evaluation function
	 */	
	public int getEvaluationValue(GameState gameState){
		
		int[][] table = {{3, 4, 5, 7, 5, 4, 3}, 
				        {4, 6, 8, 10, 8, 6, 4},
				        {5, 8, 11, 13, 11, 8, 5}, 
				        {5, 8, 11, 13, 11, 8, 5},
				        {4, 6, 8, 10, 8, 6, 4},
				        {3, 4, 5, 7, 5, 4, 3}};
		int value = 0;
		
		for (int col=0; col<GameState.NUM_COLS; col++){
			for (int row=0; row<GameState.NUM_ROWS; row++){
				if (gameState.getCounterAt(col, row) == GameState.RED){
					value += table[row][col];
				} else if (gameState.getCounterAt(col, row) == GameState.YELLOW){
					value -= table[row][col];
				}				
			}
		}
		return value;	
	}
		
	/**
	 * (Maximising)
	 * choose the move with the highest evaluation value
	 * @param gameState the current state of the game
	 * @return the column which has been chosen
	 */
	public int chooseMove(GameState gameState){
		
		//best column to move
		int bestCol = 0;
		
		int comparedValue = Integer.MIN_VALUE;
		
		//try all possible moves
		for (int i=0; i< GameState.NUM_COLS; i++){
			
			//copy the gameState for each possible move
			GameState newState = (GameState) gameState.copy(); 
		  	    
			if (!newState.isColumnFull(i)){
			    dropCounter(i, newState);
			      
			    //alpha-beta
			    int alpha = Integer.MIN_VALUE;
			    int beta = Integer.MAX_VALUE;
			    int value = minimax(0, GameState.YELLOW, newState, alpha, beta);
			    
			    //compare to find the largest value and corresponding column
			    if (value > comparedValue){
			    	comparedValue = value;
			    	bestCol = i;
			    }			
		    }
		}
		return bestCol;			
	}	
	
	/**
	 * implementing mini-max algorithm and alpha-beta pruning
	 * @param depth the depth of search tree
	 * @param turn current turn of the game
	 * @param gameState currentState of the game
	 * @return the value for evaluation function
	 */
	public int minimax(int depth, int turn, GameState gameState, int alpha, int beta){
		
		//check if the game is over
		if (gameState.gameOver()){
			if (gameState.getWinner()==0)
				return Integer.MAX_VALUE;
			else 
				return Integer.MIN_VALUE;
		}
		
		//check if depthLimit is reached
		if (depth == this.depth){
			return getEvaluationValue(gameState);
		} 
		
		depth++;
		if (turn==GameState.RED){
			
			//maximising player
			for (int i=0; i<GameState.NUM_COLS; i++){
				
				//copy gameState for each possible move
				GameState newState = (GameState) gameState.copy();
			    
				if (!newState.isColumnFull(i)){
					dropCounter(i, newState);
				    int value = minimax(depth, GameState.YELLOW, newState, alpha, beta);
				    if (value > alpha){
				    	alpha = value;
				    }
				    if (alpha >= beta){
				    	return alpha;
				    }
				}				
			}				
			return alpha;			
		} else { //YELLOW player
			
			//minimising player
			for (int i=0; i<GameState.NUM_COLS; i++){
				
				//copy gameState for each possible move
				GameState newState = (GameState) gameState.copy();
			    
			    if (!newState.isColumnFull(i)){
			    	dropCounter(i, newState);
				    int value = minimax(depth, GameState.RED, newState, alpha, beta);
				    			    		    			    
				    if (value < beta){
				    	beta = value;
				    }
				    
				    if (beta <= alpha){
				    	return beta;
				    }
			    }			
		    }
			return beta;
		}
	}

}
