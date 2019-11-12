package uk.ac.sheffield.aca13pn;

import sheffield.*;

public class PlayConnect4 {
    public static void main(String[] args){
		
		EasyReader reader = new EasyReader();
		EasyWriter writer = new EasyWriter();
		
		//choose the mode to play
		writer.println("There are three modes to play:\n\t1. Human vs Stupid Computer\n\t2. Human vs Intelligent computer\n\t3."
				+ " Human vs Human");
		int mode = reader.readInt("Enter mode (1 to 3): ");
		
		//invalid mode
		while (mode > 3 || mode < 1) {
			writer.println("Invalid mode !");
			mode = reader.readInt("Enter mode again: ");
		}
		
		if (mode == 1){
			
			//Play against random computer
			Connect4Player red = new RandomPlayer();
		    Connect4Player yellow = new KeyboardPlayer();
		    
		    Connect4 game = new Connect4(red, yellow);
			game.play();
			
		} else if (mode ==2){
			
			//play against intelligent computer
			//variable depth
			int depth = reader.readInt("Enter depth limit: ");
			Connect4Player red = new IntelligentPlayer(depth);
		    Connect4Player yellow = new KeyboardPlayer();
		    
		    Connect4 game = new Connect4(red, yellow);
			game.play();
		} else {
			
			//run tournament
			Tournament tournament = new Tournament();
			tournament.run();
		}
	}
} 
