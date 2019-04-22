//********************************************************************
//  Player.java       
//
//  
//********************************************************************
public class Player {

	private String name;
	private int wins;
	private int losses;
	private int currentPoints;
	private int runningTotal;

    //-----------------------------------------------------------------
    //  Creates a Player with basic information.
    //-----------------------------------------------------------------
	public Player(String name)
	{
		this.name = name;
		wins = 0;
		losses = 0;
		currentPoints = 0;
		runningTotal = 0;
	}

    //-----------------------------------------------------------------
    //  Returns the number of times the Player lost.
    //-----------------------------------------------------------------
	public int getLosses()
	{
		return this.losses;
	}

    //-----------------------------------------------------------------
    //  Returns the number of times the Player won.
    //-----------------------------------------------------------------
	public int getWins()
	{
		return this.losses;
	}

    //-----------------------------------------------------------------
    //  Setter method for the Player's name
    //-----------------------------------------------------------------
	public void setName(String name)
	{
		this.name = name;
	}

    //-----------------------------------------------------------------
    //  Getter method for the Player's name
    //-----------------------------------------------------------------
	public String getName()
	{
		return name;
	}

	//-----------------------------------------------------------------
    //  Updates the player's stats
    //-----------------------------------------------------------------
	public void lost()
	{
		losses++;
	}

	//-----------------------------------------------------------------
    //  Updates the player's stats
    //-----------------------------------------------------------------
	public void won()
	{
		wins++;
	}

	//-----------------------------------------------------------------
    //  Accessor method for the current points 
    //-----------------------------------------------------------------
	public int getPoints()
	{
		return this.currentPoints;
	}

	//-----------------------------------------------------------------
    //  Accessor method for the player's running point total 
    //-----------------------------------------------------------------
	public int getTotalPoints()
	{
		return runningTotal;
	}

	//-----------------------------------------------------------------
    //  Mutator method for updating the player's points 
    //-----------------------------------------------------------------
	public void setPoints(int points)
	{
		this.currentPoints = points;
		this.runningTotal+=points;
	}

	//-----------------------------------------------------------------
    //  String representation of a player
    //-----------------------------------------------------------------
	public String toString()
	{
		String result = "\n"+name+": Running total: "+runningTotal+"\n\t"+
						"wins: "+wins+"\n\tlosses: "+losses+"\n";
		return result;
	}
}