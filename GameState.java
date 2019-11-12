package uk.ac.sheffield.aca13pn;

public class GameState extends Connect4GameState {
	
	/** A 2D array to act as the board */
	int[][] board;
	
	/** A variable to control the turn */
	int turn;
		
	/**
	 * Constructor
	 */
	public GameState(){
		board = new int[NUM_ROWS][NUM_COLS];
		turn = RED; //RED goes first
	}
	
	/**
	 * Constructor
	 * @param oldTurn
	 * @param oldBoard
	 */
	public GameState(int oldTurn, int[][] oldBoard){
		turn = oldTurn;
		board = oldBoard;
	}
	
	
	/**
	 * Setup the board 
	 */
	public void startGame() {
		for (int y  = 0; y < NUM_ROWS; y++){
			for (int x = 0; x < NUM_COLS; x++){
				board[y][x] = EMPTY;
			}
		}
	}

	/**
	 * Drop the counter at the column
	 * @param col the column in which to drop the counter, in the range 0-6
	 * @throws ColumnFullException if the column denoted by col is full (i.e. the move cannot be played)
	 * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
	 */
	public void move(int col) throws ColumnFullException, IllegalColumnException {
		if (col > NUM_COLS-1 || col < 0) throw new IllegalColumnException(col);
		int y = NUM_ROWS-1; 
		boolean filled = false;
		while (y >= 0 && filled == false){
			if (board[y][col] == EMPTY){
				if (turn % 2 == 0) board[y][col] = RED;
				else board[y][col] = YELLOW;
				filled = true;
			}
			y--;
		}		
		if (y < 0 && filled == false) throw new ColumnFullException(col);
	}

	public int whoseTurn() {
		return turn % 2;
	}

	/**
	 * Returns a constant denoting the status of the slot at the position denoted by the
	 * col and row parameters
	 * @param col the column of the position being queried (in the range 0-6)
	 * @param row the row of the position being queried (in the range 0-5)
	 * @return the EMPTY constant if the slot is empty, the RED constant(0) if the slot is filled by a red counter,
	 * the YELLOW constant(1) if is yellow
	 * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
	 * @throws IllegalRowException if col is not in the range 0-5 (i.e. an invalid row)
	 */
	public int getCounterAt(int col, int row) throws IllegalColumnException, IllegalRowException {
		if (col < 0 || col > NUM_COLS-1) throw new IllegalColumnException(col);
				
		if (row < 0 || row > NUM_ROWS-1) throw new IllegalRowException(row);
		
		return board[row][col];
	}

	public boolean isBoardFull() {
		for (int y = 0; y < NUM_ROWS; y++){
			for (int x = 0; x < NUM_COLS; x++){
				if (board[y][x] == EMPTY) return false;
			}
		}
		return true;
	}

	/**
	 * Check whether or not a particular column is full
	 * @return TRUE if it is full, and FALSE otherwise
	 */
	public boolean isColumnFull(int col) throws IllegalColumnException {
		for (int y = NUM_ROWS-1; y >= 0; y--){
			if (board[y][col] == EMPTY) return false;
		}
		return true;
	}

	/**
	 * Work out the winner of the game
	 * @return Red constant (0) if Red wins, Yellow constant(1) if yellow wins, Empty if it draws
	 */
	public int getWinner() {
		int count = 1;
				
		//check all horizontal lines
		for (int y = NUM_ROWS-1; y >= 0; y--){
			int x = 0;
			while (x < 4){
				if (board[y][x] == board[y][x+count]
						&& (board[y][x]==0 || board[y][x]==1)){
					count++;
				} else {
					x += count;
					count = 1;
				}
				if (count == NUM_IN_A_ROW_TO_WIN) return board[y][x];
			}
			count = 1;
	    }
		
		//check all vertical lines
		for (int x = 0; x < NUM_COLS; x++){
			int y = 0;
			while (y < 3){
				if (board[y][x] == board[y+count][x]
						&& (board[y][x]==0 || board[y][x]==1)){
					count++;
				} else {
					y += count;
					count = 1;
				}
				if (count == NUM_IN_A_ROW_TO_WIN) return board[y][x];
			}
			count = 1;
	    }
		
		//check diagonal lines
		for (int x = 0; x <= 3 ; x++){
			for (int y = NUM_ROWS-1; y >= 3; y--){
				if (board[y][x] == board[y-1][x+1]
						&& board[y][x] == board[y-2][x+2]
								&& board[y][x] == board[y-3][x+3]
										&& (board[y][x]==0 || board[y][x]==1))
					return board[y][x];				
			}
		}
		
		//check diagonal lines
		for (int x = NUM_COLS-1; x >= 3 ; x--){
			for (int y = NUM_ROWS-1; y >= 3; y--){
				if (board[y][x] == board[y-1][x-1]
						&& board[y][x] == board[y-2][x-2]
								&& board[y][x] == board[y-3][x-3]
										&& (board[y][x]==0 || board[y][x]==1))
					return board[y][x];
			}
		}				
		return -1;
	}

	/**
	 * check whether game is over or not
	 * @return TRUE if board full or found winner, and FALSE otherwise
	 */
	public boolean gameOver() {		
	    return (isBoardFull() || (getWinner() != EMPTY)) ;
	}

	/**
	 * Copy the state of the game and paste it to a new object
	 * Deep coping...
	 * @return the new object 
	 */
	public Connect4GameState copy() {
		int[][] newBoard = new int[NUM_ROWS][NUM_COLS];
		for (int i=0; i<NUM_ROWS; i++){
			for (int j=0; j<NUM_COLS; j++){
				newBoard[i][j] = getCounterAt(j, i);
			}
		}
		return new GameState(this.turn, newBoard);
	}
}
