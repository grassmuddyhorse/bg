/***********************************************************************************************
 * Copyright @ 2014, Ours_Team
 * File name:   PlayerManagement.java
 * Author:  longdao      Date:  2014-8-27
 * 
 * Description:    // Bowing game player management
 *                 
 * History:       //  
 * *********************************************************************************************/
package BowlingGame;
import java.util.Hashtable;

public class PlayerManagement
{
	// save the players' info
	private static Hashtable<Integer,TypeDef.playerStruc> playerTable = new Hashtable<Integer,TypeDef.playerStruc>(); 
	
	private static TypeDef.High_Score _highScore =new TypeDef.High_Score();
	
	/*
	 * set the playerTable
	 * 
	 */
	public static int setPlayerTable(Hashtable<Integer,TypeDef.playerStruc> player_Table)
	{
		playerTable=player_Table;
		return 1;
	}
	
	/*
	 * get the playerTable
	 * 
	 */
	public static Hashtable<Integer,TypeDef.playerStruc> getPlayerTable()
	{
		  return playerTable;
	}

	/*
	 * get the High Score
	 */
	public static TypeDef.High_Score getHighScore() {
		return _highScore;
	}

	/*
	 * set the High Score
	 */
	public static void setHighScore(TypeDef.High_Score _highScore) {
		PlayerManagement._highScore = _highScore;
	}
	
	public static int SpareScore = -1;
}
