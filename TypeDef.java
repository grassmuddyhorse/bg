/***********************************************************************************************
 * Copyright @ 2014, Ours_Team
 * File name:   TypeDef.java
 * Author:  longdao      Date:  2014-10-15
 * 
 * Description:    // define the new type
 *                 
 * History:       //  
 * *********************************************************************************************/
package BowlingGame;

public class TypeDef
{
	/// <summary>
    /// the player's struct
    /// </summary>
    public static class playerStruc
    {
        public String playerName;
        public String playerRecord;
        public int playerScore;
    }
    
    
    /*
     * 
     * 
     * one time,
     * player play score and flag 
     * which identifies whether this time is complete,
     * timeCount identifies the added score from where
     */

    public static class TimeFlag
    {
        public int timeScore;
        public int timeCount;     
        public boolean timeFlag;
    }
    
    /*
     *  player play time and score,toal 10 grids =10 times
     */
    public static class timeScore
    {
        public TimeFlag time1Score=new TimeFlag();
        public TimeFlag time2Score=new TimeFlag();
        public TimeFlag time3Score=new TimeFlag();
        public TimeFlag time4Score=new TimeFlag();
        public TimeFlag time5Score=new TimeFlag();
        public TimeFlag time6Score=new TimeFlag();
        public TimeFlag time7Score=new TimeFlag();
        public TimeFlag time8Score=new TimeFlag();
        public TimeFlag time9Score=new TimeFlag();
        public TimeFlag time10Score=new TimeFlag();
    }
    

    /*
     *  player's high score
     */
    public static class High_Score
    {
        public String highName;
        public int highScore;
    }
}