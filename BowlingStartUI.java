/***********************************************************************************************
 * Copyright @ 2014, Ours_Team
 * File name:   BowlingStartUI.java
 * Author:  longdao      Date:  2014-8-25
 * 
 * Description:    // Bowing game starting interface
 *                 
 * History:       //  
 * *********************************************************************************************/ 
package BowlingGame;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.*;   


@SuppressWarnings("serial")
public class BowlingStartUI extends JPanel implements ActionListener   
{ 
	
	/*
	 * define the control
	 * 
	 */
	 JFrame startFrame;
	 JLabel  jlTitle;
	 JButton jbHC;
	 JButton jbStart;
	 JButton jbLoad;
	 
	 JLabel  jlNext;
	 JTextField  jtfPlayerNu;
	 JButton jbNext;
	 
	 Hashtable<Integer,TypeDef.playerStruc> playerTable = new Hashtable<Integer,TypeDef.playerStruc>();
	 
	/* 
	 *  initialize the start frame
	 * 
	 */
	public BowlingStartUI()
	{
		startFrame = new JFrame(); 

		startFrame.setLocationByPlatform(true);  
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.getContentPane().setBackground(Color.gray);
		startFrame.setResizable(false);
		startFrame.setSize(330, 300); 
		startFrame.setLocationRelativeTo(null); // center the startFrame 
		startFrame.setLayout(null);
	
		 // define and set title label
		jlTitle=new JLabel("Bowling Game");
		jlTitle.setSize(141, 19);
		jlTitle.setFont(new Font("ËÎÌå",Font.BOLD,20));
		
        // define and set "HIGH SCORE" button
		jbHC=new JButton("HIGH SCORE");
		jbHC.setSize(117, 28);
		jbHC.setBackground(Color.pink);
		jbHC.addActionListener(this);
		
		// define and set "START" button
		jbStart=new JButton("START");
		jbStart.setSize(95, 40);
		jbStart.setBackground(Color.pink);
		jbStart.addActionListener(this);
		
		// define and set "LOAD" button
		jbLoad=new JButton("LOAD");
		jbLoad.setSize(95, 40);
		jbLoad.setBackground(Color.pink);
		jbLoad.addActionListener(this);
		
		// add the "HIGH SCORE"¡¢"START"¡¢"LOAD" button
		startFrame.add(jlTitle);
		jlTitle.setLocation(new Point(95, 43));
		startFrame.add(jbHC);
		jbHC.setLocation(new Point(105, 83));
		startFrame.add(jbStart);
		jbStart.setLocation(new Point(55, 160));
		startFrame.add(jbLoad);
		jbLoad.setLocation(new Point(180, 160));
		
		startFrame.setVisible(true);  
		playerTable=PlayerManagement.getPlayerTable();
	}
	
	
	/*
	 * main fuction
	 * 
	 */
	public static void main(String[] args)  
	{      
		EventQueue.invokeLater(new Runnable()  
        { 
			public void run()  
            {
				@SuppressWarnings("unused")
				BowlingStartUI startUI=new BowlingStartUI();
            }
	    });
	}
	
	
	
	/*
	 * 
	 * click the button,trigger the following events
	 * 
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==jbHC)
		{// click the "HIGH SCORE"button,trigger the following events
			// read the high score
            @SuppressWarnings("unused")
			HighScoreUI highForm = new HighScoreUI();
		}
		else if(e.getSource()==jbStart)
		{// click the "START"button,trigger the following events
			
			// hide the prime control
			this.jbHC.setVisible(false);
			this.jbLoad.setVisible(false);
			this.jbStart.setVisible(false);
			this.jlTitle.setVisible(false);
			
            // define and set hint label 
			jlNext=new JLabel("How many players(1-4)");
			jlNext.setSize(250,25);
			jlNext.setFont(new Font("ËÎÌå",Font.BOLD,20));
			
			// define and set text field
			jtfPlayerNu=new JTextField();
			jtfPlayerNu.setSize(53, 21);
			jtfPlayerNu.addKeyListener(new jtfPlayerNuKeyListener());
			// define and set "NEXT" button
            jbNext=new JButton("NEXT");  
            jbNext.setSize(95, 40);
            jbNext.addActionListener(new jbNextAction());
            jbNext.setBackground(Color.pink);
            
			// add the new contrl,hint label¡¢ text¡¢"NEXT"button
			startFrame.add(jlNext);
			jlNext.setLocation(new Point(55, 43));
			startFrame.add(jtfPlayerNu);
			jtfPlayerNu.setLocation(new Point(135, 100));
			startFrame.add(jbNext);
			jbNext.setLocation(new Point(115, 160));
			
		}
		else if(e.getSource()==jbLoad)
		{// click the "LOAD"button,trigger the following events
			try {
				LoadData();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	/*
	 * Fuction:click the "LOAD"button,trigger the following events
	 */
	private void LoadData() throws IOException
	{
		 // read the game score
        ArrayList<String> recordList=GameProgress.GetInstance().GetRecordLine(Constants.RECORD_PATH);
        if (recordList.size()<=1)
        {
        	JOptionPane.showMessageDialog(this,"no record");
            return;
        }

        int lineNu=0,key = 1;
        for (int i = recordList.size()-1; i >= 0; i--)
        {
            String record=recordList.get(i);
            if (record.equals("") && i != (recordList.size() - 1))
            { lineNu = i; break; }  // locate the latest record
        }

        while (lineNu<recordList.size()-1)
        {
            String line = recordList.get(++lineNu);
            if (!line.equals(""))
            {
            	TypeDef.playerStruc player = new TypeDef.playerStruc();
                String[] items = line.split("\t");
                player.playerRecord = items[0];
                player.playerName = items[1];
                player.playerScore = Integer.parseInt(items[2]);
                playerTable.put(key, player);
                key=key+1;
            }
        }
        PlayerManagement.setPlayerTable(playerTable);
        startFrame.setVisible(false);
        @SuppressWarnings("unused")
		GameMainUI gMain = new GameMainUI(startFrame,true);
	}
	
	
	/*
	 * click the "NEXT"button,trigger the following events
	 * 
	 */
	class jbNextAction implements  ActionListener{ 
		public void actionPerformed(ActionEvent e) 
		{
			if(jtfPlayerNu.getText().trim().length()<1){return;}
			if((Integer.parseInt(jtfPlayerNu.getText().toString())>4)||
			  (Integer.parseInt(jtfPlayerNu.getText().toString())<1))
			{ // verify the input(1~4),if illegal return
				return;
			}
			else
			{ // show the player management UI
				startFrame.setVisible(false);     // hide the start frame
				@SuppressWarnings("unused")
				PlayerManagerUI playerMU=new PlayerManagerUI(Integer.parseInt(jtfPlayerNu.getText().toString()),startFrame);
			}
		}
	}
	
	/*
	 * 
	 *  the "jtfPlayerNu" text, verify the input 
	 * 
	 */
	class jtfPlayerNuKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			int keyChar=e.getKeyChar();
			if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {}
			else {e.consume();  }
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}

	}

}