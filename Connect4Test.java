package uk.ac.sheffield.aca13pn;

import org.junit.*;
import static org.junit.Assert.*;

public class Connect4Test {
	private KeyboardPlayer yellow;
	private RandomPlayer red;
	private GameState state;
		
	@Before
	public void setUp() {
		yellow = new KeyboardPlayer();
		red = new RandomPlayer();
		state = new GameState();
		state.startGame();
	}
	
	/**
	 * Junit test for class GameState
	 */
	
	@Test
	public void moveTest()  throws IllegalColumnException, ColumnFullException {
		state.move(3);
		int result = state.board[5][3];
		assertEquals(0, result);
	}
	
	@Test
	public void whoseTurnTest(){
		assertEquals(0, state.whoseTurn());
	}
	
	@Test
	public void getCounterAtTest(){
		assertEquals(-1, state.getCounterAt(1, 2));
	}
	
	@Test
	public void isBoardFullTest(){
		assertFalse(state.isBoardFull());
	}
	
	@Test
	public void isColumnFullTest(){
		assertFalse(state.isColumnFull(3));
	}
	
	@Test
	public void getWinnerTest() throws IllegalColumnException, ColumnFullException{
		state.turn = 1;
		for (int i=1; i<5; i++){
			state.move(2);
		}
		assertEquals(1, state.getWinner());
	}
	
	@Test
	public void gameOverTest(){
		assertFalse(state.gameOver());
	}
	
	/**
	 * Junit Test for class RandomPlayer
	 */
	
	@Test
	public void redEnterColTest(){
		int col = red.enterCol();
		assertTrue(col >=0 && col <=6);
	}
	
	@Test 
	public void redDropCounterTest(){
		red.dropCounter(4, state);
		int result = state.board[5][4];
		assertEquals(0, result);
	}
	
	@Test
	public void redMakeMoveTest(){
		red.makeMove(state);
		boolean found = false;
		for (int i=0; i <7; i++){
			if (state.board[5][i] == 0)
				found = true;
		}
		assertTrue(found);
	}
	
	/**
	 * Junit Test for KeyBoardPlayer class
	 */
	
	@Test 
	public void yellowDropCounterTest(){
		yellow.dropCounter(4, state);
		int result = state.board[5][4];
		assertEquals(0, result);
	}
	
	
}
