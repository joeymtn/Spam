/**
 * @(#)KeyboardThread.java
 *
 * KeyboardThread application
 *
 * @author 
 * @version 1.00 2018/12/12
 */
 //Joey Version

//import statements
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;
import javax.swing.JOptionPane;

public class KeyboardThread extends Thread
{
	// for inputting file data
	private static final String TEXT_FILE_NAME = "spambotText.txt";
	//essentially the amount of seconds something is spammed for 
    private int duration;
    //database that holds all input from String 
   	private static ArrayList<String> notepadDatabase = new ArrayList<String>();
   	private String text;
   	private boolean running;
   	
   	//setters
  	public void setText(String str)
  	{
  		this.text = str;
  	}
  	public void setDuration(int duration)
  	{
  		if(duration < 0)
  		{
  			throw new IllegalArgumentException("Duration can't be zero!");
  		}
  		else
  	
  		this.duration = duration;
  	}
  	//getters
  	public String getText()
  	{
  		return this.text;
  	}
  	public int getDuration()
  	{
  		return this.duration;
  	}
  	public static ArrayList<String> getArray()
  	{
  		return notepadDatabase;
  	}
  	//retrieves text file from a specified location
  	// adds them into the notepadDatabase
  	public void retrieveTextFileInput()
  	{
  		try
  		{
  			Scanner inputStream = new Scanner(new FileInputStream(TEXT_FILE_NAME));
  			
  			
  			while(inputStream.hasNextLine())
  			{
  				String placeHolder = inputStream.nextLine();
  				notepadDatabase.add(placeHolder);
  			}
  			for(String concatenator : notepadDatabase)
  			{
  				concatenator =  concatenator + " ";
  				setText(concatenator);
  			}
  			
  		
  			
  		}
  		catch(IOException e)
  		{
  			System.out.println(e);
  			System.out.println("file input error");
  		}
  	}
  	public KeyboardThread()
  	{
  		this.running = false;
  	}
  	
  	//full constructor 
  	public void updateSettings(int duration, String text)
   	{
   		setDuration(duration);
   		setText(text);
   	}
   	//enter text method, utilizes robot to take command of the keyboard and simulate the copying and pasting of text
   	public boolean pasteText()
   	{
   		try
   		{
   			Robot r  = new Robot();
   			/*
   			 * Stores information to system clipboard
   			 * StringSelection is a tool used for data transfer between systems i.e. necessary for transferring system clipboard
   			 */
   			 
   			StringSelection copiedText = new StringSelection(text);
   			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
   			cb.setContents(copiedText,copiedText);
   			/*
   			 * Simulating Ctrl - V Pasting 
   			 */
   			r.keyPress(KeyEvent.VK_CONTROL);
   			r.keyPress(KeyEvent.VK_V);
   			r.keyRelease(KeyEvent.VK_Z);
   			r.keyRelease(KeyEvent.VK_CONTROL);
   			r.keyPress(10);
   			r.keyRelease(10);
   			
   			Thread.sleep(200);
   			return true;
   		}
   		catch(AWTException e)
   		{
   			e.printStackTrace();
   			return false;
   		}
   		catch(InterruptedException a)
   		{
   			a.printStackTrace();
   			return false;
   		}
   		
   	}
   	//checks to see if at any point in the thread, there is an error that needs to be fixed
   	public void run()
   	{
   		for(int i = 0; i < duration; i++)
   		{
   			if(running)
   			{
   				if(!pasteText())
   				{
   					System.out.println("Keyboard error!");
   				}
   			}
   		}
   	}
   	public static void main(String[] args)
   	{
   		KeyboardThread test = new KeyboardThread();
   		
   		test.retrieveTextFileInput();
   		test.setText("HEY EVAN YOU'RE A NOOB!");
   	
   		for(String display : KeyboardThread.notepadDatabase)
   		{
   			System.out.println(display);
   		}
   		SpamBot spambot = new SpamBot();
   	}
    
}
