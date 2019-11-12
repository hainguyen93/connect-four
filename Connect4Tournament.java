package uk.ac.sheffield.aca13pn;
import java.util.List;
import java.util.Map;

public abstract class Connect4Tournament {
	
	public abstract void runTournament(Map<String, Connect4Player> competitors);
	
	public abstract List<Connect4Ranking> getRankings();
	
	public abstract void printTable();
}
