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
	   TypeDef.playerStruc player=new TypeDef.playerStruc();
	   
	   Hashtable<Integer,TypeDef.playerStruc> playerTestTable=new Hashtable<Integer,TypeDef.playerStruc>();
	   player.playerName="jam";
	   playerTestTable.put(1, player);
	   assertEquals(1, PlayerManagement.setPlayerTable(playerTestTable));    // set the player table,should be 1
	   assertEquals(playerTestTable, PlayerManagement.getPlayerTable());     // get the player table,should be playerTestTable
   
   }
   public static void main(String[] args) 
   { 
	   junit.textui.TestRunner.run(PlayerManagement.class); 
   } 
}