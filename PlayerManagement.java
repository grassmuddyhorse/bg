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
	private static Hashtable<Integer,String> playerTable = new Hashtable<Integer,String>(); 
	
	/*
	 * set the playerTable
	 * 
	 */
	public int setPlayerTable(Hashtable<Integer,String> player_Table)
	{
		playerTable=player_Table;
		return 1;
	}
	
	/*
	 * get the playerTable
	 * 
	 */
	public Hashtable<Integer,String> getPlayerTable()
	{
		  return playerTable;
	}
	
	/*
	 * 
	 * playerTable.put(¡°one¡±, new Integer(1)); 
¡¡¡¡¡¡¡¡	Integer n = (Integer)playerTable.get(¡°two¡±); 
	 * 
	 */
}
