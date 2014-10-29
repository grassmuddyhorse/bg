/***********************************************************************************************
 * Copyright @ 2014, Ours_Team
 * File name:   GameProgress.java
 * Author:  longdao      Date:  2014-10-23
 * 
 * Description:    // Bowing game progress record,implement GameRecord class
 *                 
 * History:       //  
 * *********************************************************************************************/
package BowlingGame;
class GameProgress extends  GameRecord
{
	 //private static string m_gameRecord = string.Empty;// game process record
    private static GameRecord instance = null;      // Instance


    // GameProgress class Instance
    public static GameRecord GetInstance()
    {
        if (instance == null)
        {
            instance = new GameProgress();
        }
        return instance;
    }
}