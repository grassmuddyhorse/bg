/***********************************************************************************************
 * Copyright @ 2014, Ours_Team
 * File name:   testBowlingGame.java
 * Author:  longdao      Date:  2014-8-27
 * 
 * Description:    // test class PlayerManagement
 *                 
 * History:       //  
 * *********************************************************************************************/ 
package BowlingGame;
import java.util.Hashtable;

import junit.framework.TestCase; 

public class TestBowlingGame extends TestCase { 

/*
 * test class PlayerManagement,succeed
 * 
 * 
 */

   public void testPlayerManagement()
   {
	   PlayerManagement playerMa=new PlayerManagement(); 
	   Hashtable<Integer,String> playerTestTable=new Hashtable<Integer,String>();
	   playerTestTable.put(1, "jam");
	   assertEquals(1, playerMa.setPlayerTable(playerTestTable));    // set the player table,should be 1
	   assertEquals(playerTestTable, playerMa.getPlayerTable());     // get the player table,should be playerTestTable
   
   }
   public static void main(String[] args) 
   { 
	   junit.textui.TestRunner.run(PlayerManagement.class); 
   } 
}