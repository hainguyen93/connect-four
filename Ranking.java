package uk.ac.sheffield.aca13pn;

public class Ranking extends Connect4Ranking {
    
	//each object has a name and a score
	String name;
	int point;
	
	/**
	 * Constructor
	 * @param name name of player
	 * @param point total point achieved so far
	 */
	public Ranking(int point, String name){
		this.name = name;
		this.point = point;
	}
	
	public String getName(){
		return name;
	}
	
	public int getPoints(){
		return point;
	}

}
