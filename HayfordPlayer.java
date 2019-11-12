package uk.ac.sheffield.aca13pn;

public class HayfordPlayer extends Connect4Player {
	
	double randomCol;
	int col;
		
	
	public void getPoint(int point){
		this.point += point;
	}
	
	@Override
	public void makeMove(Connect4GameState gameState) {
		
	//generate a random number 
		randomCol = Math.random();
		col = (int) (randomCol*10);
		
	//execute gamestate methods
	
				try {
					gameState.move(col);
				} catch (IllegalColumnException e) {				
					System.out.println("The column number produced was invalid");
					randomCol = Math.random();
					col = (int) (randomCol*10);
					try {
						gameState.move(col);
					} catch (IllegalColumnException e1) {
					} catch (ColumnFullException e1) {
					}
				} catch (ColumnFullException e) {
					System.out.println("The selected column is full");
					randomCol = Math.random();
					col = (int) (randomCol*10);
					try {
						gameState.move(col);
					} catch (IllegalColumnException e1) {	
					} catch (ColumnFullException e1) {
					}
				}	
			}
}
