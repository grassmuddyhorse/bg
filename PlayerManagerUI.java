/***********************************************************************************************
 * Copyright @ 2014, Ours_Team
 * File name:   PlayerManagerUI.java
 * Author:  longdao      Date:  2014-8-26
 * 
 * Description:    // Bowing game player management interface
 *                 
 * History:       //  
 * *********************************************************************************************/ 
package BowlingGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PlayerManagerUI extends JPanel implements ActionListener
{
	JFrame  playerManagerFrame;
	JLabel  jlTitle;
	JButton jbStart;
	JButton jbBack;
	JFrame startUI;
	int playerNum;
	/*
	 * initialize the player management interface
	 * 
	 * 
	 */
	public PlayerManagerUI(int playerNu, JFrame startUI)
	{ 
		this.startUI=startUI;
		this.playerNum=playerNu;
		playerManagerFrame=new JFrame();
		playerManagerFrame.setLocationByPlatform(true);  
		playerManagerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playerManagerFrame.getContentPane().setBackground(Color.gray);
		playerManagerFrame.setResizable(false);
		playerManagerFrame.setSize(330, 300); 
		playerManagerFrame.setLocationRelativeTo(null); // center the startFrame 
		playerManagerFrame.setLayout(null);
		
		// define and set the "jlTitle" control
		jlTitle=new JLabel("Enter player names");
		jlTitle.setSize(365, 288);
		jlTitle.setFont(new Font("ËÎÌו",Font.BOLD,20));
		
		// define and set the "START" button
		jbStart=new JButton("START");
		jbStart.setSize(90, 30);
		jbStart.setBackground(Color.pink);
		
		// define and set the "START" button
		jbBack=new JButton("BACK");
		jbBack.setSize(90, 30);
		jbBack.setBackground(Color.pink);
		
		// according to player's number,generate the following control
		for(int i=0;i<playerNu;i++)
		{
			String playerName="Player "+(i+1)+":";
			JLabel  jlPlayerName=new JLabel(playerName);
			jlPlayerName.setSize(50,25);
			
			String jtfName="Player"+(i+1);
			JTextField jtfPlayer=new JTextField();
			jtfPlayer.setName(jtfName);
			jtfPlayer.setSize(Constants.TB_MAX_LEN, Constants.TB_MAX_WIDTH);
			
			playerManagerFrame.add(jlPlayerName);
			jlPlayerName.setLocation(Constants.POS_X+Constants.POS_X_ADD, (i+1)*Constants.POS_Y);
			playerManagerFrame.add(jtfPlayer);
			jtfPlayer.setLocation(Constants.POS_X+Constants.POS_Y_ADD, (i+1) * Constants.POS_Y);
			
		}
		
        // add control
		playerManagerFrame.add(jlTitle);
		jlTitle.setLocation(65, -120);
		playerManagerFrame.add(jbStart);
		jbStart.setLocation(50, 230);
		jbStart.addActionListener(this);
		playerManagerFrame.add(jbBack);
		jbBack.setLocation(200, 230);
		jbBack.addActionListener(this);
		
		playerManagerFrame.setVisible(true);
	}
	
	
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==jbBack)
		{ // click the "HIGH SCORE"button,trigger the following events
			playerManagerFrame.setVisible(false);
			startUI.setVisible(true);
		}else if(e.getSource()==jbStart)
		{ // click the "START"button,trigger the following events
			
			PlayerManagement.getPlayerTable().clear();
			Hashtable<Integer,TypeDef.playerStruc> playerTable = new Hashtable<Integer,TypeDef.playerStruc>(); 
			
			for(int i=0;i<this.playerNum;i++)
			{// find all control
				for(Component com:playerManagerFrame.getRootPane().getContentPane().getComponents()){
					 if((com instanceof JTextField)){
						if(!((JTextField)com).getText().toString().equals(""))
					    {
							if (((JTextField)com).getName().equals(("Player" + (i + 1)))){  
								TypeDef.playerStruc player = new TypeDef.playerStruc();
	                            player.playerName=((JTextField)com).getText();
	                            player.playerRecord="";
	                            playerTable.put((i + 1),player);
	                            break;}
					    }else {return;}}
			    }
			}
            PlayerManagement.setPlayerTable(playerTable);
			playerManagerFrame.setVisible(false);
			try {
				@SuppressWarnings("unused")
				GameMainUI mainUI=new GameMainUI(this.startUI,false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}