package uk.ac.sheffield.aca13pn;
import sheffield.*;

public class Connect4 {
		
	GameState gameState;
	
	/** The two players */
	Connect4Player red, yellow;
	
	/**
	 * result of the game
	 */
	String result;
	
	/** The reader the read the input from the keyboard */
	EasyReader reader;
	
	/**
	 * Constructor
	 */
	public Connect4(Connect4Player red, Connect4Player yellow){
		this.red = red;
		this.yellow = yellow;
		reader = new EasyReader();
		gameState = new GameState();
		result = "";
	}
	
	public String getResult(){
		return result;
	}
	
	/**
	 * Print the current state of the board
	 */
	public void printBoard(){
		for (int y = 0; y < GameState.NUM_ROWS; y++){
			
			System.out.printf("%6c",'|');
			for (int x = 0; x < GameState.NUM_COLS; x++){
				int e = gameState.board[y][x];
				if (e == GameState.RED){
					System.out.printf("%3c",'R');
				} else if (e == GameState.YELLOW){
					System.out.printf("%3c",'Y');
				} else {
					System.out.printf("%3s"," ");
				}
				
			}
			System.out.println("  |");
			
		}
		System.out.println("      -----------------------");
		System.out.printf("%6s", " ");
		for (int i = 0; i < GameState.NUM_COLS; i++){
			System.out.printf("%3d", i);
		}
		System.out.println();
		
	}
	
	/**
	 * Method to control the game
	 */
	public void play(){
		
		gameState.startGame();
		printBoard();
		
		while(!gameState.gameOver()){
			if (gameState.whoseTurn() == GameState.RED){
				System.out.print("("+(gameState.turn+1)+")");
				red.makeMove(gameState);
				gameState.turn++;
			} else {
				System.out.print("("+(gameState.turn+1)+")");
				yellow.makeMove(gameState);
				gameState.turn++;
			}
			
			printBoard();
			switch (gameState.getWinner()){
			case GameState.YELLOW: 
				System.out.println("Y wins"); 
				result = "Y";
				break;
			case GameState.RED: 
				System.out.println("R wins"); 
				result = "R";
				break;
			}		
		}
		
		if (gameState.isBoardFull()){
			System.out.println("Draw!");
			result = "Draw";
		}
		
	}
	
	
	

}