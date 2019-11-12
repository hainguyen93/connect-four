package uk.ac.sheffield.aca13pn;

import sheffield.*;
import java.util.*;

/**
 * Code for tournament
 * @author Phan Trung Hai Nguyen
 */
public class Tournament extends Connect4Tournament {
	
	EasyReader reader;
	EasyWriter writer;
	Ranking ranking;
	Map<String, Connect4Player> finalList;
	
	/**
	 * Constructor
	 */
	public Tournament(){
		reader = new EasyReader();
		writer = new EasyWriter();
	}
    
	//run the tournament
	public void run(){
		runTournament(getPlayer());
		printTable();
	}
	
	public void runTournament(Map<String, Connect4Player> competitors){
		
		//store all names into a list
		List<String> name = new ArrayList<String>(competitors.keySet());
		
		for (int i=0; i<competitors.size()-1; i++){
					
			//player 1
			Connect4Player player1 = competitors.get(name.get(i));
						
			for (String key: name.subList(i+1, competitors.size())){
		    	
		    	//player2
		    	Connect4Player player2 = competitors.get(key);
		    	
		    	//start the game between two players
				for (int j = 1; j <= 2; j++){
					
					Connect4 game;
					writer.println(name.get(i)+" VS "+key);
					
					if (j == 1){
											
						//player1 goes first
						game = new Connect4(player1, player2);
						writer.println(name.get(i)+" goes first");
						game.play();
						
					} else {
						
						//player1 goes second
						game = new Connect4(player2, player1);
						writer.println(key+" goes first");
						game.play();
					}
											
					//increase point for players
					String result = game.getResult();
					if (result.equals("Draw")){
						writer.println("Draw !");
						player2.getPoint(1);
						player1.getPoint(1);			
						
					} else if ((result.equals("R") && (j==1))
							|| (result.equals("Y") && (j==2))){
						writer.println(name.get(i) + " won ");
						player1.getPoint(3);
						
					}else{
				    	writer.println(key+" won");
				    	player2.getPoint(3);
				    	
				    }			
				}
			}
		}
		finalList = competitors;
	}
	
	/**
	 * Ranking all competitors in descending order
	 * @return List of Connect4Ranking object ordered descending
	 */
	public List<Connect4Ranking> getRankings(){
		
		List<Connect4Ranking> list = new ArrayList<Connect4Ranking>();
		
		for (String key: finalList.keySet()){ //key: player's name
			Connect4Player player = finalList.get(key);
		    list.add(new Ranking(player.point, key));
		}
		
		//list needs to be returned
		List<Connect4Ranking> returnList = new ArrayList<Connect4Ranking>();
		int size = list.size();
				
		//sorting the list in descending order
		for (int i=0; i<size; i++){
			Connect4Ranking highest = new Ranking(0,"");
			int highestPoint = 0;
			for (Connect4Ranking obj: list){
				if (obj.getPoints()>highestPoint){
					highestPoint = obj.getPoints();
					highest = obj;
				}
			}
			list.remove(highest);
			returnList.add(highest);
		}
		return returnList;
	}
	
	/**
	 * Print a table of ranked competitors
	 */
	public void printTable(){
		writer.printf("%5s", "RANKING OF THE TOURNAMENT: ");
		writer.println();
		for (Connect4Ranking obj: getRankings()){
			writer.printf("%5s %7d ", obj.getName(), obj.getPoints());		
			writer.println();
		}
	}
	
	/**
	 * Enter the players into the game
	 */
	public Map<String, Connect4Player> getPlayer(){
		
		Map<String, Connect4Player> competitors = new HashMap<String, Connect4Player>();
			
		//add players to competitors list
		competitors.put("Hai", new IntelligentPlayer(5));
		competitors.put("Hayford", new HayfordPlayer());
		competitors.put("Shallom", new ShallomPlayer());
		
		return competitors;		
	}
}