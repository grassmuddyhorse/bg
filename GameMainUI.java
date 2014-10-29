/***********************************************************************************************
 * Copyright @ 2014, Ours_Team
 * File name:   GameMainUI.java
 * Author:  longdao      Date:  2014-10-9
 * 
 * Description:    // Bowing game main UI
 *                 
 * History:       //  
 * *********************************************************************************************/ 
package BowlingGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameMainUI implements ActionListener   {
	JFrame  gameMainFrame;
	JFrame  startUI;
	boolean mFlag=false;
	
	JPanel  playerListPanel;
	JPanel  playerScorePanel;
	
	JButton goBowingButton;
	JButton strikeButton;
	JButton spareButton;
	JButton saveButton;
	JButton returnButton;
	JButton exitButton;
	JTextField GameField;
	JTextArea GameArea;                                                   // Game Record show
	
	
	private int m_gridCount = 0;                                          // cast time
    private TypeDef.timeScore m_totalScore =new TypeDef.timeScore();      // cast score
    private int m_playerTime = 1;                                         // the completed player number 
    
	Hashtable<Integer,TypeDef.playerStruc> playerTable = new Hashtable<Integer,TypeDef.playerStruc>();

	public GameMainUI(JFrame startUI,boolean flag) throws IOException
	{ 
		this.startUI=startUI;
		this.mFlag=flag;
		
		gameMainFrame=new JFrame();
		gameMainFrame.setLocationByPlatform(true);  
		gameMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameMainFrame.getContentPane().setBackground(Color.gray);
		gameMainFrame.setResizable(false);
		gameMainFrame.setSize(800, 500); 
		gameMainFrame.setLocationRelativeTo(null); // center the startFrame 
		gameMainFrame.setLayout(null);
		gameMainFrame.setVisible(true);
		
		// add the player list panel
		playerListPanel=new JPanel();
		playerListPanel.setSize(800, 100);
		playerListPanel.setBackground(Color.LIGHT_GRAY);
		gameMainFrame.add(playerListPanel);
		playerListPanel.setLayout(null);
		playerListPanel.setLocation(new Point(0,0));

		
		// add the player score panel
		playerScorePanel=new JPanel();
		playerScorePanel.setSize(800, 330);
		playerScorePanel.setBackground(Color.LIGHT_GRAY);
		gameMainFrame.add(playerScorePanel);
		playerScorePanel.setLayout(null);
		playerScorePanel.setLocation(new Point(0,105));
		
		// add the control button
		// GoBowing Button
		goBowingButton=new JButton();
		goBowingButton.setText("GoBowing");
		goBowingButton.setSize(100, 30);
		goBowingButton.setBackground(Color.PINK);
		goBowingButton.addActionListener(this);
		gameMainFrame.add(goBowingButton);
		goBowingButton.setLocation(new Point(40,440));

		// STRIKE Button
		strikeButton=new JButton();
		strikeButton.setText("STRIKE");
		strikeButton.setSize(90, 30);
		strikeButton.setBackground(Color.PINK);
		strikeButton.addActionListener(this);
		gameMainFrame.add(strikeButton);
		strikeButton.setLocation(new Point(180,440));
		// SPARE Button
		spareButton=new JButton();
		spareButton.setText("SPARE");
		spareButton.setSize(90, 30);
		spareButton.setBackground(Color.PINK);
		spareButton.addActionListener(this);
		gameMainFrame.add(spareButton);
		spareButton.setLocation(new Point(310,440));
		// Save Button
		saveButton=new JButton();
		saveButton.setText("Save");
		saveButton.setSize(90, 30);
		saveButton.setBackground(Color.PINK);
		saveButton.addActionListener(this);
		gameMainFrame.add(saveButton);
		saveButton.setLocation(new Point(440,440));
		// Return to Main
		returnButton=new JButton();
		returnButton.setText("Return");
		returnButton.setSize(90, 30);
		returnButton.setBackground(Color.PINK);
		returnButton.addActionListener(this);
		gameMainFrame.add(returnButton);
		returnButton.setLocation(new Point(560,440));
		// exit Button
		exitButton=new JButton();
		exitButton.setText("Exit");
		exitButton.setSize(80, 30);
		exitButton.setBackground(Color.PINK);
		exitButton.addActionListener(this);
		gameMainFrame.add(exitButton);
		exitButton.setLocation(new Point(680,440));
		InitComponent();
		InitData();
	}
	
	/*
	 * Fuction:init the Component
	 * 
	 */
	private void InitComponent()
	{
		strikeButton.setEnabled(false);
		spareButton.setEnabled(false);
		saveButton.setEnabled(false);
		returnButton.setEnabled(false);
		exitButton.setEnabled(false);
	}
	
	
	/*
	 * Fuction：Init data,get the player list
	 */
	private void InitData() throws IOException
	{
        JLabel GameLabel=new JLabel();
        GameLabel.setText("Total Ten Grids");
        playerScorePanel.add(GameLabel);
        GameLabel.setBounds(0,0,100,20);
        
        GameField=new JTextField();
        GameField.setEditable(false);
        GameField.setOpaque(true);
        GameField.setBackground(Color.lightGray);
        GameField.setFont(new Font("宋体",Font.BOLD,20));
        playerScorePanel.add(GameField);
        GameField.setBounds(10,15,770,50);
        
        GameArea=new JTextArea();
        GameArea.setEditable(false);
        GameArea.setOpaque(true);
        GameArea.setBackground(Color.GRAY);
        GameArea.setFont(new Font("宋体",Font.BOLD,20));
        playerScorePanel.add(GameArea);
        GameArea.setBounds(10,70,770,230);
        
        
        
    	playerTable=PlayerManagement.getPlayerTable();
    	
		// get and set the player list
        for (int i = 0; i < PlayerManagement.getPlayerTable().size(); i++)
        {
        	JLabel playerName = new JLabel();
        	JLabel playerTb = new JLabel();
            playerName.setText("   Player " + (i + 1) + ":    ");
            playerName.setName("Player");
            TypeDef.playerStruc player=new TypeDef.playerStruc();
            player=PlayerManagement.getPlayerTable().get(i + 1);
            
            playerTb.setName("Player " + (i + 1));
            playerTb.setText(player.playerName);
            playerTb.setSize(Constants.TB_MAX_WIDTH, Constants.TB_MAX_WIDTH/2);
            
            if (this.mFlag)
            {
                if (0==player.playerScore)
                { playerTb.setOpaque(true);playerTb.setBackground(Color.PINK); }
                else {ShowPlayerInfo(player); this.m_playerTime = i + 2; }
            }
         
            playerListPanel.add(playerName);
            playerListPanel.add(playerTb);
//            playerTb.setOpaque(true);
//            playerTb.setBackground(Color.PINK); 
            playerName.setBounds(0,(i) * Constants.POS_Y/2,100,20);
            playerTb.setBounds(100,(i) * Constants.POS_Y/2,100,20);
        }
        
        TypeDef.High_Score _highScore = new TypeDef.High_Score();
    	StringBuffer highScore =HighScore.GetInstance().GetRecord(Constants.HIGH_SCORE_PATH);
        if (highScore.length()>0)
        {
            String score =highScore.substring(0);
            String []scoreItems=score.split("[*]");
            _highScore.highName=scoreItems[0];
            _highScore.highScore=Integer.parseInt(scoreItems[1]);
            PlayerManagement.setHighScore(_highScore);
        }
	}
	
	
	/*
	 * 
	 * click the button,trigger the following events
	 * 
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==goBowingButton)
		{
			GoBowling();
			strikeButton.setEnabled(true);
			spareButton.setEnabled(true);
			saveButton.setEnabled(true);
			returnButton.setEnabled(true);
			exitButton.setEnabled(true);
		}else if(e.getSource()==strikeButton)
		{Strike();}else if(e.getSource()==spareButton)
		{Spare();}else if(e.getSource()==saveButton)
		{SaveData();}else if(e.getSource()==returnButton)
		{gameMainFrame.setVisible(false);
		// show the prime control
		@SuppressWarnings("unused")
		BowlingStartUI _start=new BowlingStartUI();
        }else if(e.getSource()==exitButton)
		{System.exit(0);}
	}
	
	
	/*
	 * Fuction:click the "SAVE" button
	 * 
	 */
	private void SaveData()
	{
		TypeDef.playerStruc player = new TypeDef.playerStruc();
        String record = "";
        for (int i = 0; i < PlayerManagement.getPlayerTable().size(); i++)
        {
        	player=PlayerManagement.getPlayerTable().get(i+1); 
            record += "\r\n" + player.playerRecord+"\t"+player.playerName + "\t" + player.playerScore;
        }
         record += "\r\n";
         WriteRecord wr=new WriteRecord(record);
         wr.start();
         saveButton.setEnabled(false);
         spareButton.setEnabled(false);
         strikeButton.setEnabled(false);
         HighScoreCount hscore=new HighScoreCount(record);
         hscore.start();
	}
	
	
	/*
	 * WriteRecord Thread
	 */
	class WriteRecord extends Thread {
		private String record;
		public WriteRecord(String record)
		{
			this.record=record;
		}
	    public void run() 
	    {
            try {
				if (GameProgress.GetInstance().SaveRecord(Constants.RECORD_PATH, this.record))
				{ JOptionPane.showMessageDialog(GameArea, "save success");  }
				else
				{ JOptionPane.showMessageDialog(GameArea,"save failed"); }
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	    }
	 }
	
	
	/*
	 * Count High Score Thread
	 */
	class HighScoreCount extends Thread {
		private String record;
		public HighScoreCount(String record)
		{
			this.record=record;
		}
	    public void run() 
	    {
	    	try {
				HighScore.GetInstance().CountHighScore(this.record);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 }
	
	
	/*
	 * click the "SPARE" button
	 * 
	 */
	private void Spare()
	{
		m_gridCount++;
        if (m_gridCount > 10) return;  // Total 10 Grids
        Object[] possibleValues = { "0", "1", "2","3","4","5","6","7","8","9" } ;
        int selectedValue = JOptionPane.showOptionDialog(null,
        	     "Input the first score", "Spare",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
        	    null,possibleValues, possibleValues[0]);
        PlayerManagement.SpareScore=selectedValue;
        
        GameField.setText(GameField.getText()+" "+PlayerManagement.SpareScore+"/|");
        switch (m_gridCount)
        {
            case 1:
                m_totalScore.time1Score.timeScore += 10;
                m_totalScore.time1Score.timeCount = 1; 
                m_totalScore.time1Score.timeFlag = false; break;
            case 2:
                if (!m_totalScore.time1Score.timeFlag)
                {
                    if (2 == m_totalScore.time1Score.timeCount)
                    {
                        m_totalScore.time1Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time1Score.timeScore += 10;
                        m_totalScore.time1Score.timeCount-=2;
                        m_totalScore.time1Score.timeFlag = true;
                    }
                    else if (1 == m_totalScore.time1Score.timeCount)
                    {
                        m_totalScore.time1Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time1Score.timeCount--;
                        m_totalScore.time1Score.timeFlag = true;
                    }
                }
                m_totalScore.time2Score.timeScore += 10;
                m_totalScore.time2Score.timeCount = 1;
                m_totalScore.time2Score.timeFlag = false; break;
            case 3:
                if (!m_totalScore.time2Score.timeFlag)
                {
                    if (2 == m_totalScore.time2Score.timeCount)
                    {
                        m_totalScore.time2Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time2Score.timeScore += 10;
                        m_totalScore.time2Score.timeCount -= 2;
                        m_totalScore.time2Score.timeFlag = true;
                    }
                    else if (1 == m_totalScore.time2Score.timeCount)
                    {
                        m_totalScore.time2Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time2Score.timeCount--;
                        m_totalScore.time2Score.timeFlag = true;
                    }
                }
                m_totalScore.time3Score.timeScore += 10;
                m_totalScore.time3Score.timeCount = 1;
                m_totalScore.time3Score.timeFlag = false; break;
            case 4:
                if (!m_totalScore.time3Score.timeFlag)
                {
                    if (2 == m_totalScore.time3Score.timeCount)
                    {
                        m_totalScore.time3Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time3Score.timeScore += 10;
                        m_totalScore.time3Score.timeCount -= 2;
                        m_totalScore.time3Score.timeFlag = true;
                    }
                    else if (1 == m_totalScore.time3Score.timeCount)
                    {
                        m_totalScore.time3Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time3Score.timeCount--;
                        m_totalScore.time3Score.timeFlag = true;
                    }
                }
                m_totalScore.time4Score.timeScore += 10;
                m_totalScore.time4Score.timeCount = 1;
                m_totalScore.time4Score.timeFlag = false; break;
            case 5:
                if (!m_totalScore.time4Score.timeFlag)
                {
                    if (2 == m_totalScore.time4Score.timeCount)
                    {
                        m_totalScore.time4Score.timeScore += PlayerManagement.SpareScore; //
                        m_totalScore.time4Score.timeScore += 10;
                        m_totalScore.time4Score.timeCount -= 2;
                        m_totalScore.time4Score.timeFlag = true;
                    }
                    else if (1 == m_totalScore.time4Score.timeCount)
                    {
                        m_totalScore.time4Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time4Score.timeCount--;
                        m_totalScore.time4Score.timeFlag = true;
                    }
                }
                m_totalScore.time5Score.timeScore += 10;
                m_totalScore.time5Score.timeCount = 1;
                m_totalScore.time5Score.timeFlag = false; break;
            case 6:
                if (!m_totalScore.time5Score.timeFlag)
                {
                    if (2 == m_totalScore.time5Score.timeCount)
                    {
                        m_totalScore.time5Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time5Score.timeScore += 10;
                        m_totalScore.time5Score.timeCount -= 2;
                        m_totalScore.time5Score.timeFlag = true;
                    }
                    else if (1 == m_totalScore.time5Score.timeCount)
                    {
                        m_totalScore.time5Score.timeScore += 5; // 随机
                        m_totalScore.time5Score.timeCount--;
                        m_totalScore.time5Score.timeFlag = true;
                    }
                }
                m_totalScore.time6Score.timeScore += 10;
                m_totalScore.time6Score.timeCount = 1;
                m_totalScore.time6Score.timeFlag = false; break;
            case 7:
                if (!m_totalScore.time6Score.timeFlag)
                {
                    if (2 == m_totalScore.time6Score.timeCount)
                    {
                        m_totalScore.time6Score.timeScore += PlayerManagement.SpareScore; //
                        m_totalScore.time6Score.timeScore += 10;
                        m_totalScore.time6Score.timeCount -= 2;
                        m_totalScore.time6Score.timeFlag = true;
                    }
                    else if (1 == m_totalScore.time6Score.timeCount)
                    {
                        m_totalScore.time6Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time6Score.timeCount--;
                        m_totalScore.time6Score.timeFlag = true;
                    }
                }
                m_totalScore.time7Score.timeScore += 10;
                m_totalScore.time7Score.timeCount = 1;
                m_totalScore.time7Score.timeFlag = false; break;
            case 8:
                if (!m_totalScore.time7Score.timeFlag)
                {
                    if (2 == m_totalScore.time7Score.timeCount)
                    {
                        m_totalScore.time7Score.timeScore += PlayerManagement.SpareScore; //
                        m_totalScore.time7Score.timeScore += 10;
                        m_totalScore.time7Score.timeCount -= 2;
                        m_totalScore.time7Score.timeFlag = true;
                    }
                    else if (1 == m_totalScore.time7Score.timeCount)
                    {
                        m_totalScore.time7Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time7Score.timeCount--;
                        m_totalScore.time7Score.timeFlag = true;
                    }
                }
                m_totalScore.time8Score.timeScore += 10;
                m_totalScore.time8Score.timeCount = 1;
                m_totalScore.time8Score.timeFlag = false; break;
            case 9:
                if (!m_totalScore.time8Score.timeFlag)
                {
                    if (2 == m_totalScore.time8Score.timeCount)
                    {
                        m_totalScore.time8Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time8Score.timeScore += 10;
                        m_totalScore.time8Score.timeCount -= 2;
                        m_totalScore.time8Score.timeFlag = true;
                    }
                    else if (1 == m_totalScore.time8Score.timeCount)
                    {
                        m_totalScore.time8Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time8Score.timeCount--;
                        m_totalScore.time8Score.timeFlag = true;
                    }
                }
                m_totalScore.time9Score.timeScore += 10;
                m_totalScore.time9Score.timeCount = 1;
                m_totalScore.time9Score.timeFlag = false; break;
            case 10:
                if (!m_totalScore.time9Score.timeFlag)
                {
                    if (2 == m_totalScore.time9Score.timeCount)
                    {
                        m_totalScore.time9Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time9Score.timeScore += 10;
                        m_totalScore.time9Score.timeCount -= 2;
                        m_totalScore.time9Score.timeFlag = true;
                    }
                    else if (1 == m_totalScore.time9Score.timeCount)
                    {
                        m_totalScore.time9Score.timeScore += PlayerManagement.SpareScore; // 
                        m_totalScore.time9Score.timeCount--;
                        m_totalScore.time9Score.timeFlag = true;
                    }
                }
                m_totalScore.time10Score.timeScore += 10;
                m_totalScore.time10Score.timeScore += 10;       // 随机全中
                m_totalScore.time10Score.timeCount = 1;
                m_totalScore.time10Score.timeFlag = true;ScoreBoard();break;
            default: break;
        }
	}
	
	/*
	 * Fuction:click the "GoBowing" button
	 * 
	 */
	private void GoBowling()
	{
		 if (!mFlag)
         {
			for(Component com:playerListPanel.getComponents())
			{
				 if((com instanceof JLabel)){
						if (((JLabel)com).getName().equals(("Player 1")))
						{   ((JLabel)com).setOpaque(true);
						    ((JLabel)com).setBackground(Color.PINK);  break;
						}
				  }
		    }
         }
	}
	
	/*
	 * click the "STRIKE" button
	 * 
	 */
	private void Strike()
	{
		m_gridCount++;
        if (m_gridCount>10) return; // Total 10 Grids
        else{GameField.setText(GameField.getText()+"  X|");}
        switch (m_gridCount)
        {
            case 1:
            	m_totalScore.time1Score.timeScore += 10;
                m_totalScore.time1Score.timeCount = 2;
                m_totalScore.time1Score.timeFlag = false; break;
            case 2:
                if (!m_totalScore.time1Score.timeFlag)
                {
                    if (2 == m_totalScore.time1Score.timeCount)
                    {m_totalScore.time1Score.timeScore += 10;
                     m_totalScore.time1Score.timeCount --;
                     m_totalScore.time1Score.timeFlag = false;}
                    else if (1 == m_totalScore.time1Score.timeCount)
                    {m_totalScore.time1Score.timeScore += 10;
                     m_totalScore.time1Score.timeCount--;
                     m_totalScore.time1Score.timeFlag = true;}
                }
                m_totalScore.time2Score.timeScore += 10;
                m_totalScore.time2Score.timeCount = 2;
                m_totalScore.time2Score.timeFlag=false;break;
            case 3:
                if (!m_totalScore.time1Score.timeFlag && (1 == m_totalScore.time1Score.timeCount))
                {
                    m_totalScore.time1Score.timeScore += 10;
                    m_totalScore.time1Score.timeCount--;
                    m_totalScore.time1Score.timeFlag = true;
                }
                if (!m_totalScore.time2Score.timeFlag)
                {
                    if (2 == m_totalScore.time2Score.timeCount)
                    {m_totalScore.time2Score.timeScore += 10;
                    m_totalScore.time2Score.timeCount--;
                     m_totalScore.time2Score.timeFlag = false;}
                    else if (1 == m_totalScore.time2Score.timeCount)
                    {
                        m_totalScore.time2Score.timeScore += 10;
                        m_totalScore.time2Score.timeCount--;
                        m_totalScore.time2Score.timeFlag = true;
                    }
                }
                m_totalScore.time3Score.timeScore += 10;
                m_totalScore.time3Score.timeCount = 2;
                m_totalScore.time3Score.timeFlag = false;break;
            case 4:
                if (!m_totalScore.time2Score.timeFlag && (1 == m_totalScore.time2Score.timeCount))
                {
                    m_totalScore.time2Score.timeScore += 10;
                    m_totalScore.time2Score.timeCount--;
                    m_totalScore.time2Score.timeFlag = true;
                }
                if (!m_totalScore.time3Score.timeFlag)
                {
                    if (2 == m_totalScore.time3Score.timeCount)
                    {
                        m_totalScore.time3Score.timeScore += 10;
                        m_totalScore.time3Score.timeCount--;
                        m_totalScore.time3Score.timeFlag = false;
                    }
                    else if (1 == m_totalScore.time3Score.timeCount)
                    {
                        m_totalScore.time3Score.timeScore += 10;
                        m_totalScore.time3Score.timeCount--;
                        m_totalScore.time3Score.timeFlag = true;
                    }
                }
                m_totalScore.time4Score.timeScore += 10;
                m_totalScore.time4Score.timeCount = 2;
                m_totalScore.time4Score.timeFlag = false;break;
            case 5:
                if (!m_totalScore.time3Score.timeFlag && (1 == m_totalScore.time3Score.timeCount))
                {
                    m_totalScore.time3Score.timeScore += 10;
                    m_totalScore.time3Score.timeCount--;
                    m_totalScore.time3Score.timeFlag = true;
                }
                if (!m_totalScore.time4Score.timeFlag)
                {
                    if (2 == m_totalScore.time4Score.timeCount)
                    {
                        m_totalScore.time4Score.timeScore += 10;
                        m_totalScore.time4Score.timeCount--;
                        m_totalScore.time4Score.timeFlag = false;
                    }
                    else if (1 == m_totalScore.time4Score.timeCount)
                    {
                        m_totalScore.time4Score.timeScore += 10;
                        m_totalScore.time4Score.timeCount--;
                        m_totalScore.time4Score.timeFlag = true;
                    }
                }
                m_totalScore.time5Score.timeScore += 10;
                m_totalScore.time5Score.timeCount = 2;
                m_totalScore.time5Score.timeFlag = false;break;
            case 6:
                if (!m_totalScore.time4Score.timeFlag && (1 == m_totalScore.time4Score.timeCount))
                {
                    m_totalScore.time4Score.timeScore += 10;
                    m_totalScore.time4Score.timeCount--;
                    m_totalScore.time4Score.timeFlag = true;
                }
                if (!m_totalScore.time5Score.timeFlag)
                {
                    if (2 == m_totalScore.time5Score.timeCount)
                    {
                        m_totalScore.time5Score.timeScore += 10;
                        m_totalScore.time5Score.timeCount--;
                        m_totalScore.time5Score.timeFlag = false;
                    }
                    else if (1 == m_totalScore.time5Score.timeCount)
                    {
                        m_totalScore.time5Score.timeScore += 10;
                        m_totalScore.time5Score.timeCount--;
                        m_totalScore.time5Score.timeFlag = true;
                    }
                }
                m_totalScore.time6Score.timeScore += 10;
                m_totalScore.time6Score.timeCount = 2;
                m_totalScore.time6Score.timeFlag = false;break;
            case 7:
                if (!m_totalScore.time5Score.timeFlag && (1 == m_totalScore.time5Score.timeCount))
                {
                    m_totalScore.time5Score.timeScore += 10;
                    m_totalScore.time5Score.timeCount--;
                    m_totalScore.time5Score.timeFlag = true;
                }
                if (!m_totalScore.time6Score.timeFlag)
                {
                    if (2 == m_totalScore.time6Score.timeCount)
                    {
                        m_totalScore.time6Score.timeScore += 10;
                        m_totalScore.time6Score.timeCount--;
                        m_totalScore.time6Score.timeFlag = false;
                    }
                    else if (1 == m_totalScore.time6Score.timeCount)
                    {
                        m_totalScore.time6Score.timeScore += 10;
                        m_totalScore.time6Score.timeCount--;
                        m_totalScore.time6Score.timeFlag = true;
                    }
                }
                m_totalScore.time7Score.timeScore += 10;
                m_totalScore.time7Score.timeCount=2;
                m_totalScore.time7Score.timeFlag = false;break;
            case 8:
                if (!m_totalScore.time6Score.timeFlag && (1 == m_totalScore.time6Score.timeCount))
                {
                    m_totalScore.time6Score.timeScore += 10;
                    m_totalScore.time6Score.timeCount--;
                    m_totalScore.time6Score.timeFlag = true;
                }
                if (!m_totalScore.time7Score.timeFlag)
                {
                    if (2 == m_totalScore.time7Score.timeCount)
                    {
                        m_totalScore.time7Score.timeScore += 10;
                        m_totalScore.time7Score.timeCount--;
                        m_totalScore.time7Score.timeFlag = false;
                    }
                    else if (1 == m_totalScore.time7Score.timeCount)
                    {
                        m_totalScore.time7Score.timeScore += 10;
                        m_totalScore.time7Score.timeCount--;
                        m_totalScore.time7Score.timeFlag = true;
                    }
                }
                m_totalScore.time8Score.timeScore += 10;
                m_totalScore.time8Score.timeCount=2;
                m_totalScore.time8Score.timeFlag = false;break;
            case 9:
                if (!m_totalScore.time7Score.timeFlag && (1 == m_totalScore.time7Score.timeCount))
                {
                    m_totalScore.time7Score.timeScore += 10;
                    m_totalScore.time7Score.timeCount--;
                    m_totalScore.time7Score.timeFlag = true;
                }
                if (!m_totalScore.time8Score.timeFlag)
                {
                    if (2 == m_totalScore.time8Score.timeCount)
                    {
                        m_totalScore.time8Score.timeScore += 10;
                        m_totalScore.time8Score.timeCount--;
                        m_totalScore.time8Score.timeFlag = false;
                    }
                    else if (1 == m_totalScore.time8Score.timeCount)
                    {
                        m_totalScore.time8Score.timeScore += 10;
                        m_totalScore.time8Score.timeCount--;
                        m_totalScore.time8Score.timeFlag = true;
                    }
                }
                m_totalScore.time9Score.timeScore += 10;
                m_totalScore.time9Score.timeCount=2;
                m_totalScore.time9Score.timeFlag = false;break;
            case 10:
                if (!m_totalScore.time8Score.timeFlag && (1 == m_totalScore.time8Score.timeCount))
                {
                    m_totalScore.time8Score.timeScore += 10;
                    m_totalScore.time8Score.timeCount--;
                    m_totalScore.time8Score.timeFlag = true;
                }
                if (!m_totalScore.time9Score.timeFlag)
                {
                    if (2 == m_totalScore.time9Score.timeCount)
                    {
                        m_totalScore.time9Score.timeScore += 20;
                        m_totalScore.time9Score.timeCount-=2;
                        m_totalScore.time9Score.timeFlag = false;
                    }
                    else if (1 == m_totalScore.time9Score.timeCount)
                    {
                        m_totalScore.time9Score.timeScore += 10;
                        m_totalScore.time9Score.timeCount--;
                        m_totalScore.time9Score.timeFlag = true;
                    }
                }
                m_totalScore.time10Score.timeScore += 10;
                m_totalScore.time10Score.timeScore += 20;      // 后两次随机全中
                m_totalScore.time10Score.timeCount=2;
                m_totalScore.time10Score.timeFlag = true;
                ScoreBoard();break;
            default:break;
        }
	}
	
	
	/*
	 * record the ex player's score,
     * start the next record
	 */
	private void ScoreBoard()
	{
		int _totalScore = m_totalScore.time1Score.timeScore + m_totalScore.time2Score.timeScore +
                m_totalScore.time3Score.timeScore + m_totalScore.time4Score.timeScore +
                m_totalScore.time5Score.timeScore + m_totalScore.time6Score.timeScore +
                m_totalScore.time7Score.timeScore + m_totalScore.time8Score.timeScore +
                m_totalScore.time9Score.timeScore + m_totalScore.time10Score.timeScore;
		// record the current player's score
		TypeDef.playerStruc player = new TypeDef.playerStruc();
		
		if (PlayerManagement.getPlayerTable().size() >= 1)
		{ player=PlayerManagement.getPlayerTable().get(this.m_playerTime); }
		player.playerScore = _totalScore;
		player.playerRecord=GameField.getText();
		playerTable.put(this.m_playerTime, player);
		PlayerManagement.setPlayerTable(playerTable);
		
        ShowPlayerInfo(player);
		// turn the next player
		this.m_gridCount = 0;    // next turn
		m_totalScore = new TypeDef.timeScore();
		this.m_playerTime += 1;
		if (PlayerManagement.getPlayerTable().size() < this.m_playerTime)
		{
		    strikeButton.setEnabled(false);
			spareButton.setEnabled(false);
            goBowingButton.setEnabled(false);
            saveButton.setEnabled(true);
            returnButton.setEnabled(true);
            exitButton.setEnabled(true);
            GameField.setText("");
		    return;
		}
		
		for(Component com:playerListPanel.getComponents())
		{
			 if((com instanceof JLabel)){
				 ((JLabel)com).setOpaque(true);
				 ((JLabel)com).setBackground(Color.LIGHT_GRAY);
					if (((JLabel)com).getName().equals("Player " + this.m_playerTime))
					{ ((JLabel)com).setBackground(Color.PINK);  break;}
			  }
	    }
		GameField.setText("");
	}
	

    /*
     * Fuction:Show Player Info
     */
    private void ShowPlayerInfo(TypeDef.playerStruc player)
    {
        String recordLine="\r\n"+player.playerRecord+"\t"+player.playerName+"\t"+player.playerScore+"\r\n";
        GameArea.setText(GameArea.getText()+recordLine);

        if (this.m_playerTime==PlayerManagement.getPlayerTable().size())
        {
            goBowingButton.setEnabled(false);
            strikeButton.setEnabled(false);
            spareButton.setEnabled(false);
            saveButton.setEnabled(false);
            returnButton.setEnabled(true);
            exitButton.setEnabled(true);
        }
    }
    
}