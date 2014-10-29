/***********************************************************************************************
 * Copyright @ 2014, Ours_Team
 * File name:   HighScoreUI.java
 * Author:  longdao      Date:  2014-10-23
 * 
 * Description:    // high score interface
 *                 
 * History:       //  
 * *********************************************************************************************/
package BowlingGame;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HighScoreUI
{
	/*
	 * define the control
	 * 
	 */
	 JFrame highScoreFrame;
	 JLabel scoreHint;
	 JLabel highScore;
	 
	public HighScoreUI()
	{
		highScoreFrame=new JFrame();
		highScoreFrame.setLocationByPlatform(true);  
//		highScoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		highScoreFrame.getContentPane().setBackground(Color.gray);
		highScoreFrame.setResizable(false);
		highScoreFrame.setSize(330, 300); 
		highScoreFrame.setLocationRelativeTo(null); // center the startFrame 
		highScoreFrame.setLayout(null);

		scoreHint=new JLabel();
		scoreHint.setText("Ranking List");
		highScoreFrame.add(scoreHint);
		scoreHint.setFont(new Font("Serif",Font.BOLD,20));
		scoreHint.setBounds(0, 0, 200, 50);
		
		highScore=new JLabel();
		highScoreFrame.add(highScore);
		highScore.setFont(new Font("Serif",Font.BOLD,30));
		highScore.setBounds(100, 100, 200, 50);
		
		highScoreFrame.setVisible(true);
		
		try {
			InitData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
    /*
     *  Fuction:init high score data
     */
    private void InitData() throws IOException
    {
    	StringBuffer _highScore =HighScore.GetInstance().GetRecord(Constants.HIGH_SCORE_PATH);
        if (_highScore.length()>0)
        {
            String score =_highScore.substring(0);
            String []scoreItems=score.split("[*]");
            String showItem = scoreItems[0] + "  :  "+scoreItems[1];
            highScore.setText(showItem);
        }
    }
}