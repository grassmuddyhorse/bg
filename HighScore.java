/***********************************************************************************************
 * Copyright @ 2014, Ours_Team
 * File name:   HighScore.java
 * Author:  longdao      Date:  2014-10-23
 * 
 * Description:    // Bowing game high score record,implement GameRecord class
 *                 
 * History:       //  
 * *********************************************************************************************/
package BowlingGame;

import java.io.IOException;

class HighScore extends GameRecord
{
	private static HighScore instance = null;      // Instance

    // HighScore class Instance
    public static HighScore GetInstance()
    {
        if (instance == null)
        {
            instance = new HighScore();
        }
        return instance;
    }

    
    /*
     * Fuction:count the high score
     */

    public void CountHighScore(String record) throws IOException
    {
    	TypeDef.High_Score highScore = null;
        String[] records = record.split("\r\n");
        for (int i = 0; i < records.length;i++ )
        {
            if (!records[i].equals(""))
            {
            	highScore=new TypeDef.High_Score();
                String[] items = records[i].split("[\t]");
                highScore.highName = items[1];
                highScore.highScore = Integer.parseInt(items[2]);
                if (PlayerManagement.getHighScore().highScore < highScore.highScore)
                {
                    PlayerManagement.setHighScore(highScore);
                }
            }
        }

        StringBuffer HighScore=GetInstance().GetRecord(Constants.HIGH_SCORE_PATH);
        if (HighScore.length()>0)
        {
            highScore=new TypeDef.High_Score();
            String[] scoreContent = HighScore.substring(0).split("[*]");
            highScore.highName = scoreContent[0];
            highScore.highScore = Integer.parseInt(scoreContent[1]);

            if (PlayerManagement.getHighScore().highScore < highScore.highScore)
            {
                PlayerManagement.setHighScore(highScore);
            }
        }
        String l_highScore = PlayerManagement.getHighScore().highName + "*" + PlayerManagement.getHighScore().highScore;
        GetInstance().ClearRecord(Constants.HIGH_SCORE_PATH);
        GetInstance().SaveRecord(Constants.HIGH_SCORE_PATH, l_highScore);
    }
}
