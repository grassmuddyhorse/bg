/***********************************************************************************************
 * Copyright @ 2014, Ours_Team
 * File name:   GameRecord.cs
 * Author:  longdao      Date:  2014-8-23
 * 
 * Description:    // Bowing game record management,abstract class
 *                 
 * History:       //  
 * *********************************************************************************************/
package BowlingGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

abstract class GameRecord
{

    /*
     * Fuction:get game or high score record
     */
	public  StringBuffer GetRecord(String path) throws IOException
    {
		StringBuffer record = new StringBuffer();
		BufferedReader recordReader = new BufferedReader(new FileReader(path));
		String recordLine;
		
		while(null!=(recordLine= recordReader.readLine()))
		{
			record.append(recordLine);
		}
        recordReader.close();
        return record;
    }


    /*
     *  Fuction:save game or high score record
     */
    public boolean SaveRecord(String path, String game_record) throws IOException
    {
    	FileWriter   myStream = null;
        try
        {
        	myStream = new FileWriter(path,true);
        	myStream.write(game_record);
        	myStream.flush();
        	myStream.close();
            return true;
        }
        catch (Exception ex)
        {
            if (null != myStream) { myStream.close(); }
            return false;	
        }
    }



    /*
     * Fuction:get game record or high score,as lines
     */

    public ArrayList<String> GetRecordLine(String path) throws IOException
    {
    	ArrayList<String> record_List =new ArrayList<String>();
        
        BufferedReader recordReader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = recordReader.readLine()) != null)
        {
            record_List.add(line);
        }
        recordReader.close();
        return record_List;
    }

    /*
     * 
     * Fuction:clear game or high score record
     */

    public boolean ClearRecord(String path) throws IOException
    {
    	BufferedWriter  myStream = null;
        try
        {
        	myStream = new BufferedWriter(new FileWriter(path));
        	myStream.write("");
        	myStream.flush();
        	myStream.close();
            return true;
        }
        catch (Exception ex)
        {
            if (null != myStream) { myStream.close(); }
            return false;	
        }

    }
}

